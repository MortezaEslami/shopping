package com.egs.shopping.service.iservice;

import com.egs.shopping.service.dto.UserProductRateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserProductRateService {

    UserProductRateDTO.Info get(Long id);

    Page<UserProductRateDTO.Info> list(Pageable pageable);

    UserProductRateDTO.Info create(UserProductRateDTO.Create request);

    UserProductRateDTO.Info update(Long id, UserProductRateDTO.Update request);

    void delete(Long id);

}
