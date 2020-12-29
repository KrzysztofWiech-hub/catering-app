package com.catering.controller;

import com.catering.model.Product;
import com.catering.model.dayOfWeekDto.ProductDayDto;
import com.catering.service.ProductService;
import com.catering.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/product/{productId}/day/{dayName}")
    public ResponseEntity addProductToDayOfWeek(@PathVariable Integer productId, @PathVariable String dayName) {
        productValidator.validateIfProductIdIsSetInDayOfWeek(productId, dayName);
        productService.addProductToDayOfWeek(productId, dayName);
        return ResponseEntity.ok(productId);
    }

    @GetMapping(value = "/product/{productId}")
    public ResponseEntity getProductByProductId(@PathVariable Integer productId) {
        productValidator.checkExistenceProductByProductId(productId);
        Product product = productService.getProductByProductId(productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity deleteProductByProductId(@PathVariable Integer productId) {
        productValidator.checkExistenceProductByProductId(productId);
        productService.deleteProductByProductId(productId);
        return ResponseEntity.ok(productId);
    }

    @DeleteMapping(value = "/product/{productId}/day/{dayName}")
    public ResponseEntity deleteProductFromDayOfWeek(@PathVariable Integer productId, @PathVariable String dayName) {
        productValidator.checkExistenceProductByProductId(productId);
        productValidator.checkExistenceProductIdInDayOfWeek(productId);
        productService.deleteProductFromDayOfWeek(productId, dayName);
        return ResponseEntity.ok(productId);
    }

    @GetMapping(value = "/products/day/{dayName}/list")
    public ResponseEntity<List<ProductDayDto>> getAllProductsOfDayByDayName(@PathVariable String dayName) {
        // TODO: 2020-12-29 finish this endpoint, fix problem with mapping object in a repository
        List<ProductDayDto> productDayDtoList = productService.getAllProductsOfDayByDayName(dayName);
        return ResponseEntity.ok(productDayDtoList);
    }
}
