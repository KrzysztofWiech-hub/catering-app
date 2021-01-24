package com.catering.controller;

import com.catering.model.Product;
import com.catering.model.Response.ProductsOfDayResponse;
import com.catering.model.dto.ProductsDayDto;
import com.catering.service.ProductService;
import com.catering.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
        productValidator.validateInputDaysNames(dayName);
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

    @GetMapping(value = "/products/all")
    public ResponseEntity getAllProducts() {
        List<Product> product = productService.getAllProducts();
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
        productValidator.checkExistenceProductIdInDayOfWeek(productId, dayName);
        productService.deleteProductFromDayOfWeek(productId, dayName);
        return ResponseEntity.ok(productId);
    }

    @GetMapping(value = "/products/day/{dayName}")
    public ResponseEntity<ProductsOfDayResponse> getAllProductsOfDayByDayName(@PathVariable String dayName) {
        productValidator.validateInputDaysNames(dayName);
        ProductsDayDto productsDayDto = productService.getAllProductsOfDayByDayName(dayName);
        ProductsOfDayResponse productsOfDayResponse = new ProductsOfDayResponse(productsDayDto);
        return ResponseEntity.ok(productsOfDayResponse);
    }

    @GetMapping(value = "/products/days/all")
    public ResponseEntity<List<ProductsOfDayResponse>> getAllProductsOfDayByDayName() {
        List<ProductsDayDto> productsDayDtoList = productService.getAllProductsFromAllDays();
        List<ProductsOfDayResponse> responseList = new ArrayList<>();

        for (ProductsDayDto productsDayDto : productsDayDtoList) {
            responseList.add(new ProductsOfDayResponse(productsDayDto));
        }
        return ResponseEntity.ok(responseList);
    }
}
