package com.egs.shopping.service;

import com.egs.shopping.exception.BadRequestInfoException;
import com.egs.shopping.exception.ResourceNotFoundException;
import com.egs.shopping.model.User;
import com.egs.shopping.repository.UserRepository;
import com.egs.shopping.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) {
        String lowercaseUserName = userName.toLowerCase();
        return userRepository.findOneWithAuthoritiesByUserName(lowercaseUserName)
                .map(this::createSecurityUser)
                .orElseThrow(() -> new ResourceNotFoundException("User " + lowercaseUserName + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSecurityUser(User user) {
        if (!user.isActivated()) {
            throw new BadRequestInfoException("User is not active !");
        }
        List<GrantedAuthority> grantedAuthorities = userRoleRepository.findByUserId(user.getId())
                .stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
