package com.egs.shopping.controller;

import com.egs.shopping.service.dto.UserProductRateDTO;
import com.egs.shopping.service.iservice.IUserProductRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user-product-rates")
public class UserProductRateRestController {

    private final IUserProductRateService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserProductRateDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserProductRateDTO.Info>> list(Pageable pageable) {
        return new ResponseEntity<>(service.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserProductRateDTO.Info> create(@Validated @RequestBody UserProductRateDTO.Create request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserProductRateDTO.Info> update(@PathVariable Long id, @RequestBody UserProductRateDTO.Update request) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
