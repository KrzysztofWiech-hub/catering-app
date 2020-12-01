package com.catering.controller;

import com.catering.model.Product;
import com.catering.service.ProductService;
import com.catering.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductValidator productValidator;

    public ProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @PostMapping(value = "/product")
    public ResponseEntity addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(value = "/product/{productId}/day/{dayName}")
    public ResponseEntity addProductToDayOfWeek(@PathVariable Integer productId, @PathVariable String dayName) {
        productValidator.checkIsProjectIdExistInDayOfWeek(productId, dayName);
        productService.addProductToDayOfWeek(productId, dayName);
        return ResponseEntity.ok().body(productId);
    }

    @GetMapping(value = "/product")
    public ResponseEntity getProductByProductId(@RequestParam Integer productId) {
        Product product = productService.getProductByProductId(productId);
        return ResponseEntity.ok().body(product);
    }
}
