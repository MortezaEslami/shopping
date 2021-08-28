package com.egs.shopping.controller;

import com.egs.shopping.service.dto.CategoryDTO;
import com.egs.shopping.service.iservice.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryRestController {

    private final ICategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO.Info> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO.Info>> list(Pageable pageable) {
        return new ResponseEntity<>(service.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO.Info> create(@Validated @RequestBody CategoryDTO.Create request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO.Info> update(@PathVariable Long id, @RequestBody CategoryDTO.Update request) {
        return new ResponseEntity<>(service.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
