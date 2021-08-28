package com.egs.shopping.controller;

import com.egs.shopping.service.dto.UserDTO;
import com.egs.shopping.service.iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO.Info>> list(Pageable pageable) {
        return new ResponseEntity<>(service.list(pageable), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDTO.Create request) {
        service.create(request);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO.JWTToken> authorize(@Valid @RequestBody UserDTO.LoginDTO loginDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + service.login(loginDTO));
        return new ResponseEntity<>(new UserDTO.JWTToken(service.login(loginDTO)),
                httpHeaders, HttpStatus.OK);
    }

    @PatchMapping(value = "/block/{userId}")
    public ResponseEntity<Void> changeBlockStatus(@PathVariable(value = "userId", required = false) final Long userId,
                                                  @NotNull @RequestBody UserDTO.BlockingDTO blockingDTO) {
        service.changeBlockStatus(userId, blockingDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
