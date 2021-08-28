package com.egs.shopping.service;

import com.egs.shopping.exception.ResourceNotFoundException;
import com.egs.shopping.model.UserProductRate;
import com.egs.shopping.repository.UserProductRateRepository;
import com.egs.shopping.service.dto.UserProductRateDTO;
import com.egs.shopping.service.iservice.IProductService;
import com.egs.shopping.service.iservice.IUserProductRateService;
import com.egs.shopping.service.iservice.IUserService;
import com.egs.shopping.service.mapper.UserProductRateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserProductRateService implements IUserProductRateService {

    private final UserProductRateRepository repository;
    private final UserProductRateMapper mapper;
    private final IProductService productService;
    private final IUserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public UserProductRateDTO.Info get(Long id) {
        final UserProductRate entity = getUserProductRate(id);
        return mapper.toDtoInfo(entity);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public Page<UserProductRateDTO.Info> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDtoInfo);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public UserProductRateDTO.Info create(UserProductRateDTO.Create request) {
        UserProductRate userProductRate = mapper.toEntityFromCreate(request);
        userProductRate.setUserId(userService.getCurrentUserId());
        UserProductRate save = repository.save(userProductRate);
        productService.updateProductRate(request.getProductId(), calculateAverageRate(request.getProductId()));
        return mapper.toDtoInfo(save);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public UserProductRateDTO.Info update(Long id, UserProductRateDTO.Update request) {
        getUserProductRate(id);
        UserProductRate entity = mapper.toEntityFromUpdate(request);
        entity.setId(id);
        return mapper.toDtoInfo(repository.save(entity));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        getUserProductRate(id);
        repository.deleteById(id);
    }

    private Double calculateAverageRate(Long productId) {
        return repository.calculateAverageRateByProductId(productId);
    }

    private UserProductRate getUserProductRate(Long id) {
        final Optional<UserProductRate> entityById = repository.findById(id);
        return entityById.orElseThrow(() -> new ResourceNotFoundException("No result found for your request id = " + id));
    }
}

