package com.egs.shopping.service.iservice;

import com.egs.shopping.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductDTO.Info get(Long id);

    Page<ProductDTO.Info> list(Pageable pageable);

    ProductDTO.Info create(ProductDTO.Create request);

    ProductDTO.Info update(Long id, ProductDTO.Update request);

    void delete(Long id);

    Page<ProductDTO.Info> filter(ProductDTO.FilterDTO filterDTO, Pageable pageable);

    void updateProductRate(Long productId, Double rate);
}
