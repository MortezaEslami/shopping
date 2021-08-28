package com.egs.shopping.service;


import com.egs.shopping.config.jwt.TokenProvider;
import com.egs.shopping.exception.BadRequestInfoException;
import com.egs.shopping.exception.ResourceNotFoundException;
import com.egs.shopping.model.Role;
import com.egs.shopping.model.User;
import com.egs.shopping.model.UserRole;
import com.egs.shopping.repository.RoleRepository;
import com.egs.shopping.repository.UserRepository;
import com.egs.shopping.repository.UserRoleRepository;
import com.egs.shopping.service.dto.UserDTO;
import com.egs.shopping.service.iservice.IUserService;
import com.egs.shopping.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final TokenProvider tokenProvider;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public UserDTO.Info get(Long id) {
        final Optional<User> entityById = repository.findById(id);
        final User entity = entityById.orElseThrow(() -> new ResourceNotFoundException("No result found for your request id = " + id));
        return mapper.toDtoInfo(entity);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public Page<UserDTO.Info> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDtoInfo);
    }

    @Override
    public String login(UserDTO.LoginDTO loginDTO) {
        return tokenProvider.provideToken(loginDTO);
    }

    public void create(UserDTO.Create request) {
        User user = mapper.toEntityFromCreate(request);
        user.setUserName(request.getUserName().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User newUser = repository.save(user);
        Optional<Role> role = roleRepository.findByName("ROLE_USER");
        if (role.isPresent()) {
            UserRole userRole = new UserRole();
            userRole.setUserId(newUser.getId());
            userRole.setRoleId(role.get().getId());
            userRoleRepository.save(userRole);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void changeBlockStatus(Long userId, UserDTO.BlockingDTO blockingDTO) {
        Optional<User> byId = repository.findById(userId);
        if (byId.isEmpty()) {
            throw new BadRequestInfoException("user does not exist");
        } else {
            User user = byId.get();
            if (user.isActivated() == blockingDTO.isActivated()) {
                throw new BadRequestInfoException("user has the same as your request block status");
            }
            user.setActivated(blockingDTO.isActivated());
            repository.save(user);
        }
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails securityUser = (UserDetails) authentication.getPrincipal();
        Optional<User> oneByUserName = repository.findOneByUserName(securityUser.getUsername());
        return oneByUserName.map(User::getId).orElse(null);
    }
}