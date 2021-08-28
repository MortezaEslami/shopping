package com.egs.shopping.service;

import com.egs.shopping.exception.ResourceNotFoundException;
import com.egs.shopping.model.Product;
import com.egs.shopping.repository.ProductRepository;
import com.egs.shopping.service.dto.ProductDTO;
import com.egs.shopping.service.iservice.IProductService;
import com.egs.shopping.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public ProductDTO.Info get(Long id) {
        Product entity = getProduct(id);
        return mapper.toDtoInfo(entity);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public Page<ProductDTO.Info> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDtoInfo);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public ProductDTO.Info create(ProductDTO.Create request) {
        return mapper.toDtoInfo(repository.save(mapper.toEntityFromCreate(request)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public ProductDTO.Info update(Long id, ProductDTO.Update request) {
        getProduct(id);
        Product entity = mapper.toEntityFromUpdate(request);
        entity.setId(id);
        return mapper.toDtoInfo(repository.save(entity));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        getProduct(id);
        repository.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Transactional(readOnly = true)
    @Override
    public Page<ProductDTO.Info> filter(ProductDTO.FilterDTO filterDTO, Pageable pageable) {
        return repository.findAll(ProductFilterSpecifications.createFilter(filterDTO),
                pageable).map(mapper::toDtoInfo);
    }

    @Override
    public void updateProductRate(Long productId, Double rate) {
        repository.updateRateByProductId(rate, productId);
    }

    private Product getProduct(Long id) {
        final Optional<Product> entityById = repository.findById(id);
        return entityById.orElseThrow(() -> new ResourceNotFoundException("No result found for your request id = " + id));
    }


}

