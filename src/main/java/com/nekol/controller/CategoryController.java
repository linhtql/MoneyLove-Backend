package com.nekol.controller;

import com.nekol.payload.request.CategoryRequest;
import com.nekol.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    ResponseEntity<?> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(categoryService.create(request));
    }

    @GetMapping("/{type}")
    ResponseEntity<?> getCategory(@PathVariable String type) {
        if (type.equals("out-come")) {
            return ResponseEntity.ok().body(categoryService.getByOutCome());
        }
        return ResponseEntity.ok().body(categoryService.getByInCome());
    }

    @GetMapping("/update/{type}")
    ResponseEntity<?> getCategoryUserAndType(@PathVariable String type) {
        return ResponseEntity.ok().body(categoryService.getUpdate(type));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody CategoryRequest request, @PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().body("Delete category successfully!");
    }
}
