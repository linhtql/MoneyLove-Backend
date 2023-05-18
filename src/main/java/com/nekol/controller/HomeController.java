package com.nekol.controller;

import com.nekol.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/out-come")
    public ResponseEntity<?> getOutCome() {
        return ResponseEntity.ok().body(categoryService.statistic("out-come"));
    }

    @GetMapping("/in-come")
    public ResponseEntity<?> getIncome() {
        return ResponseEntity.ok().body(categoryService.statistic("in-come"));
    }
}
