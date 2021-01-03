package com.catering.service;

import com.catering.constants.Day;
import com.catering.model.Product;
import com.catering.model.dto.ProductsDayDto;
import com.catering.repository.DaysOfWeekRepository;
import com.catering.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    @Override
    public void addProductToDayOfWeek(Integer productId, String dayName) {
        Day[] allDays = Day.values();
        for (Day day : allDays) {
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

    @Override
    public List<Product> getAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Transactional
    @Override
    public void deleteProductByProductId(int productId) {
        boolean isExist = daysOfWeekRepository.existsProductById(productId);
        if (isExist) {
            daysOfWeekRepository.deleteProductFromDaysOfWeek(productId);
            productRepository.deleteProductByProductId(productId);
        } else {
            productRepository.deleteProductByProductId(productId);
        }
    }

    @Transactional
    @Override
    public void deleteProductFromDayOfWeek(int productId, String dayName) {
        Day[] allDays = Day.values();
        for (Day day : allDays) {
            if (day.getName().equalsIgnoreCase(dayName)) {
                String currentDayName = day.getName();

                Product product = getProductByProductId(productId);
                int productIOfDb = product.getId();
                daysOfWeekRepository.deleteProductFromDaysOfWeekByProductIdAndDayName(productIOfDb, currentDayName);
            }
        }
    }

    @Override
    public ProductsDayDto getAllProductsOfDayByDayName(String dayName) {
        List<Integer> allProductsIdOfDay = daysOfWeekRepository.selectAllProductsIdOfDayByDayName(dayName);
        List<Product> productList = new ArrayList<>(allProductsIdOfDay.size());

        addProductsOfDayToProductList(allProductsIdOfDay, productList);

        return new ProductsDayDto(dayName, productList);
    }

    @Override
    public List<ProductsDayDto> getAllProductsFromAllDays() {
        List<String> daysNames = Day.getArrayDaysNames();
        List<ProductsDayDto> responseList = new ArrayList<>();

        for (String dayName : daysNames) {
            List<Integer> allProductsIdOfDay = daysOfWeekRepository.selectAllProductsIdOfDayByDayName(dayName);

            List<Product> productList = new ArrayList<>(allProductsIdOfDay.size());
            addProductsOfDayToProductList(allProductsIdOfDay, productList);

            ProductsDayDto productsDayDto = buildProductsOfDayDto(dayName, productList);
            addProductsOfDayDtoToResponseList(responseList, productsDayDto);
        }
        return responseList;
    }

    private void addProductsOfDayDtoToResponseList(List<ProductsDayDto> responseList, ProductsDayDto productsDayDto) {
        responseList.add(productsDayDto);
    }

    private ProductsDayDto buildProductsOfDayDto(String dayName, List<Product> productList) {
        return new ProductsDayDto(dayName, productList);
    }

    private void addProductsOfDayToProductList(List<Integer> allProductsIdOfDay, List<Product> productList) {
        for (Integer productId : allProductsIdOfDay) {
            Product product = getProductOfDayByProductId(productId);
            productList.add(product);
        }
    }

    private Product getProductOfDayByProductId(Integer productId) {
        return productRepository.selectProductByProductId(productId);
    }
}
