package com.egs.shopping.repository;

import com.egs.shopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query("update Product p set p.rate = ?1 where p.id = ?2")
    void updateRateByProductId(Double rate, Long productId);
}
