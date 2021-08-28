package com.egs.shopping.controller;

import com.egs.shopping.service.dto.ProductDTO;
import com.egs.shopping.service.iservice.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductRestController {

    private final IProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO.Info>> list(Pageable pageable) {
        return new ResponseEntity<>(service.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO.Info> create(@Validated @RequestBody ProductDTO.Create request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO.Info> update(@PathVariable Long id, @RequestBody ProductDTO.Update request) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<Page<ProductDTO.Info>> filter(@RequestBody ProductDTO.FilterDTO filterDTO, Pageable pageable) {
        return new ResponseEntity<>(service.filter(filterDTO, pageable), HttpStatus.OK);
    }

}
