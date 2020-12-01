package com.catering.service;

import com.catering.model.Product;
import com.catering.repository.DaysOfWeekRepository;
import com.catering.repository.ProductRepository;
import com.catering.util.Days;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DaysOfWeekRepository daysOfWeekRepository;

    public ProductServiceImpl(ProductRepository productRepository, DaysOfWeekRepository daysOfWeekRepository) {
        this.productRepository = productRepository;
        this.daysOfWeekRepository = daysOfWeekRepository;
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        productRepository.insertProduct(product.getName(), product.getDescription(), product.getCost(),
                product.getKcal(), product.getMass(), product.getHeight(), product.getWeight());
    }

    @Override
    public void addProductToDayOfWeek(Integer productId, String dayName) {
        Days[] allDays = Days.values();
        for (Days day : allDays) {
            if (day.getName().equalsIgnoreCase(dayName)) {
                String currentDayName = day.getName();

                Product product = getProductByProductId(productId);
                int productIOfDb = product.getId();
                daysOfWeekRepository.insertProductToDayOfWeek(currentDayName, productIOfDb);
            }
        }
    }

    @Override
    public Product getProductByProductId(Integer id) {
        return productRepository.selectProductByProductId(id);
    }
}
