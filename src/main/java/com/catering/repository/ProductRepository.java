package com.catering.repository;

import com.catering.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM cateringdb.product PRODUCT WHERE PRODUCT.id = :id",
            nativeQuery = true)
    Product selectProductByProductId(@Param("id") Integer id);

    @Modifying
    @Query(value = "insert into cateringdb.product (name, description, cost, kcal, mass, height, weight) values (" +
            ":name, :description, :cost, :kcal, :mass,:height,:weight)"
            , nativeQuery = true)
    void insertProduct(@Param("name") String name, @Param("description") String description, @Param("cost") Double cost,
                       @Param("kcal") Double kcal, @Param("mass") Double mass, @Param("height") Double height,
                       @Param("weight") Double weight);

    @Query(value = "SELECT (1) FROM cateringdb.days_of_week DAYS_OF_WEEK WHERE" +
            " DAYS_OF_WEEK.product_id = :productId AND DAYS_OF_WEEK.day_name = :dayName",
            nativeQuery = true)
    Integer selectExistProductIdInSelectedDayOfWeek(@Param("productId") Integer productId, @Param("dayName") String dayName);

    @Query(value = "SELECT (1) FROM cateringdb.days_of_week DAY_OF_WEEK WHERE" +
            " DAY_OF_WEEK.product_id = :productId",
            nativeQuery = true)
    Integer selectExistProductByProductId(@Param("productId") Integer productId);

    @Modifying
    @Query(value = "DELETE PRODUCT FROM cateringdb.product PRODUCT where PRODUCT.id = :productId",
            nativeQuery = true)
    void deleteProductByProductId(int productId);
}
