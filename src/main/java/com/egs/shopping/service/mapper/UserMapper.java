package com.egs.shopping.service.mapper;

import com.egs.shopping.model.User;
import com.egs.shopping.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper extends CommonMapper<User, UserDTO.Info, UserDTO.Create, UserDTO.Update> {


    @Mappings({

            @Mapping(target = "password", expression = "java(null)")
    })
    @Override
    UserDTO.Info toDtoInfo(User user);
}
