package com.egs.shopping.service.mapper;

import com.egs.shopping.model.Category;
import com.egs.shopping.service.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CategoryMapper extends CommonMapper<Category, CategoryDTO.Info, CategoryDTO.Create, CategoryDTO.Update> {

}
