package com.catering.model.dto;

import com.catering.model.Product;

import java.util.List;

public class ProductsDayDto {

    private String dayName;
    private List<Product> productList;

    public ProductsDayDto(String dayName, List<Product> productList) {
        this.dayName = dayName;
        this.productList = productList;
    }

    public String getDayName() {
        return dayName;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
