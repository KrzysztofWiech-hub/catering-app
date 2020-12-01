package com.catering.validator;

import com.catering.exception.ResourceNotFoundException;
import com.catering.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void checkIsProjectIdExistInDayOfWeek(Integer productId, String dayName) {
        Integer existProjectId = productRepository.selectExistProjectIdInDayOfWeek(productId, dayName);
        if (existProjectId != null) {
            throw new ResourceNotFoundException("Chosen project is currently set to this day of week");
        }
    }

}
