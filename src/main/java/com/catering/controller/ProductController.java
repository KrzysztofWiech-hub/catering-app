package com.catering.controller;

import com.catering.service.dish.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping(value = "/dish")
//    public ResponseEntity getAllDishByDayOfTheWeek() {
////        List allDevice = productService.getAllDishByDayOfWeek();
//        return ResponseEntity.ok().body();
//    }
}
