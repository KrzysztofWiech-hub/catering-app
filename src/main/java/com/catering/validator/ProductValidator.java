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

    public void validateIfProductIdIsSetInDayOfWeek(Integer productId, String dayName) {
        Integer productExistence = productRepository.selectExistProductIdInSelectedDayOfWeek(productId, dayName);
        if (productExistence != null) {
            throw new ResourceNotFoundException("Chosen productId is currently set to this day of week");
        }
    }

    public void checkExistenceProductByProductId(Integer productId) {
        Integer productExistence = productRepository.selectExistProductByProductId(productId);
        if (productExistence == null) {
            throw new ResourceNotFoundException("Chosen productId is not existed");
        }
    }

    public void checkExistenceProductIdInDayOfWeek(Integer productId) {
        Integer productExistence = productRepository.selectExistProductByProductId(productId);
        if (productExistence == null) {
            throw new ResourceNotFoundException("Chosen productId is not existed in selected day");
        }
    }

//    public void checkExistenceProductIdInAllDaysOfWeek(Integer productId) {
//        Integer productExistence = productRepository.selectExistProductIdInAllDaysOfWeek(productId);
//        if (productExistence != null) {
//            throw new ResourceNotFoundException("Chosen productId is currently set to this day of week");
//        }
//    }
}
