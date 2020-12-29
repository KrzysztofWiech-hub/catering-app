package com.catering.service;

import com.catering.model.Product;
import com.catering.model.dayOfWeekDto.ProductDayDto;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void addProductToDayOfWeek(Integer productId, String dayName);

    Product getProductByProductId(Integer id);

    void deleteProductByProductId(int productId);

    void deleteProductFromDayOfWeek(int productId, String dayOfWeek);

    List<ProductDayDto> getAllProductsOfDayByDayName(String dayName);

}
