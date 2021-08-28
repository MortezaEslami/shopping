package com.egs.shopping.repository;

import com.egs.shopping.model.UserProductRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRateRepository extends JpaRepository<UserProductRate, Long> {

    @Query(value = "select AVG(N_RATE) from tbl_user_product_rate where N_PRODUCT_ID = ?1" ,nativeQuery = true)
    Double  calculateAverageRateByProductId(Long productId);
}
