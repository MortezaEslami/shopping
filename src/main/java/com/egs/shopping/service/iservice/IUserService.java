package com.egs.shopping.service.iservice;


import com.egs.shopping.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserDTO.Info get(Long id);

    Page<UserDTO.Info> list(Pageable pageable);

    void create(UserDTO.Create request);

    void changeBlockStatus(Long userId, UserDTO.BlockingDTO blockingDTO);

    String login(UserDTO.LoginDTO loginDTO);

    Long getCurrentUserId();

}