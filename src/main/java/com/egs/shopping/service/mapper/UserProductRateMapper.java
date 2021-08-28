package com.egs.shopping.service.mapper;

import com.egs.shopping.model.UserProductRate;
import com.egs.shopping.service.dto.UserProductRateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserProductRateMapper extends CommonMapper<UserProductRate, UserProductRateDTO.Info, UserProductRateDTO.Create, UserProductRateDTO.Update> {

}
