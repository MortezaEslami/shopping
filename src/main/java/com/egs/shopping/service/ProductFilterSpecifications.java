package com.egs.shopping.service;

import com.egs.shopping.model.Product;
import com.egs.shopping.service.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;

public class ProductFilterSpecifications {

    public static Specification<Product> createFilter(ProductDTO.FilterDTO filter) {
        Specification<Product> result = Specification.where(null);

        if (filter.getName() != null) {
            result = result.and((Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getFromPrice() != null) {
            result = result.and((Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getFromPrice()));
        }
        if (filter.getToPrice() != null) {
            result = result.and((Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getToPrice()));
        }
        if (filter.getFromRate() != null) {
            result = result.and((Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.greaterThanOrEqualTo(root.get("rate"), filter.getFromPrice()));
        }
        if (filter.getToRate() != null) {
            result = result.and((Specification<Product>) (root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.lessThanOrEqualTo(root.get("rate"), filter.getToPrice()));
        }

        return result;
    }
}