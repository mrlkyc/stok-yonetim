package com.example.stok_yonetim.controller;

import com.example.stok_yonetim.dto.CreateProductRequest;
import com.example.stok_yonetim.entity.Product;
import com.example.stok_yonetim.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }
}

