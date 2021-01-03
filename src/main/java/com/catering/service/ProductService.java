package com.catering.service;

import com.catering.model.Product;
import com.catering.model.dto.ProductsDayDto;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void addProductToDayOfWeek(Integer productId, String dayName);

    Product getProductByProductId(Integer id);

    List<Product> getAllProducts();

    void deleteProductByProductId(int productId);

    void deleteProductFromDayOfWeek(int productId, String dayOfWeek);

    ProductsDayDto getAllProductsOfDayByDayName(String dayName);

    List<ProductsDayDto> getAllProductsFromAllDays();


}
