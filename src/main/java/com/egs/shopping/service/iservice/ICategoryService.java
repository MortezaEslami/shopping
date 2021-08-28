package com.egs.shopping.service.iservice;

import com.egs.shopping.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    CategoryDTO.Info get(Long id);

    Page<CategoryDTO.Info> list(Pageable pageable);

    CategoryDTO.Info create(CategoryDTO.Create request);

    CategoryDTO.Info update(Long id, CategoryDTO.Update request);

    void delete(Long id);

}
