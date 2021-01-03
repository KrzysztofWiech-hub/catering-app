package com.catering.model.Response;

import com.catering.model.Product;
import com.catering.model.dto.ProductsDayDto;

import java.util.List;

public class ProductsOfDayResponse {

    private String dayName;
    private List<Product> productList;

    public ProductsOfDayResponse(ProductsDayDto productsDayDto) {
        this.dayName = productsDayDto.getDayName();
        this.productList = productsDayDto.getProductList();
    }

    public String getDayName() {
        return dayName;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
