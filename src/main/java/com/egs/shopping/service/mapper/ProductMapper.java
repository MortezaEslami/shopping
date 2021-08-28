package com.egs.shopping.service.mapper;

import com.egs.shopping.model.Product;
import com.egs.shopping.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CommonMapper<Product, ProductDTO.Info, ProductDTO.Create, ProductDTO.Update> {

    @Mappings({

            @Mapping(source = "category.name", target = "categoryName")
    })
    @Override
    ProductDTO.Info toDtoInfo(Product product);
}
