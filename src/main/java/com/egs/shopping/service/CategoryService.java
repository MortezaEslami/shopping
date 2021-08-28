package com.egs.shopping.service;

import com.egs.shopping.exception.ResourceNotFoundException;
import com.egs.shopping.model.Category;
import com.egs.shopping.repository.CategoryRepository;
import com.egs.shopping.service.dto.CategoryDTO;
import com.egs.shopping.service.iservice.ICategoryService;
import com.egs.shopping.service.mapper.CategoryMapper;
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
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public CategoryDTO.Info get(Long id) {
        Category entity = getCategory(id);
        return mapper.toDtoInfo(entity);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Override
    public Page<CategoryDTO.Info> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDtoInfo);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public CategoryDTO.Info create(CategoryDTO.Create request) {
        return mapper.toDtoInfo(repository.save(mapper.toEntityFromCreate(request)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public CategoryDTO.Info update(Long id, CategoryDTO.Update request) {
        getCategory(id);
        Category entity = mapper.toEntityFromUpdate(request);
        entity.setId(id);
        return mapper.toDtoInfo(repository.save(entity));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        getCategory(id);
        repository.deleteById(id);
    }

    private Category getCategory(Long id) {
        final Optional<Category> entityById = repository.findById(id);
        return entityById.orElseThrow(() -> new ResourceNotFoundException("No result found for your request id = " + id));
    }

}

