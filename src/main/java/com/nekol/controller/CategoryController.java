package com.nekol.controller;

import com.nekol.payload.request.CreateCategoryRequest;
import com.nekol.payload.request.RegisterRequest;
import com.nekol.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok().body(categoryService.create(request));
    }

    @GetMapping
    ResponseEntity<?> get() {
        return ResponseEntity.ok().body("test");
    }

}
