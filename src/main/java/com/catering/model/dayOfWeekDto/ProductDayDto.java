package com.catering.model.dayOfWeekDto;

public class ProductDayDto {

    private String dayName;
    private int productId;
    private String productName;

    public ProductDayDto(String dayName, Integer productId, String productName) {
        this.dayName = dayName;
        this.productId = productId;
        this.productName = productName;
    }

    public String getDayName() {
        return dayName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
