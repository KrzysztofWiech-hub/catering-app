package com.catering.service;

import com.catering.model.Product;

public interface ProductService {

    void addProduct(Product product);

    void addProductToDayOfWeek(Integer productId, String dayName);

    Product getProductByProductId(Integer id);
}
