package com.catering.validator;

import com.catering.constants.Day;
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
        Integer productExistence = productRepository.checkExistenceProductByProductId(productId);
        if (productExistence == null) {
            throw new ResourceNotFoundException("Chosen productId is not existed");
        }
    }

    public void checkExistenceProductIdInDayOfWeek(Integer productId, String dayName) {
        Integer productExistence = productRepository.selectExistProductIdInSelectedDayOfWeek(productId, dayName);
        if (productExistence == null) {
            throw new ResourceNotFoundException("Chosen productId is not existed in selected day of week");
        }
    }

    public void validateInputDaysNames(String inputDayName) {
        String dayName = Day.getDayNameIfInputMatch(inputDayName);
        if (dayName == null) {
            throw new ResourceNotFoundException("The day name is incorrect. Please enter correct day name");
        }
    }
}
