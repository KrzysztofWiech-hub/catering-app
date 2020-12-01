package com.catering.repository;

import com.catering.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM cateringdb.product WHERE product.id = :id",
            nativeQuery = true)
    Product selectProductByProductId(@Param("id") Integer id);

    @Modifying
    @Query(value = "insert into cateringdb.product (name, description, cost, kcal, mass, height, weight) values (" +
            ":name, :description, :cost, :kcal, :mass,:height,:weight)"
            , nativeQuery = true)
    void insertProduct(@Param("name") String name, @Param("description") String description, @Param("cost") Double cost,
                       @Param("kcal") Double kcal, @Param("mass") Double mass, @Param("height") Double height,
                       @Param("weight") Double weight);

    @Query(value = "SELECT (1) FROM cateringdb.product, cateringdb.days_of_week WHERE" +
            " product.id = :productId AND days_of_week.day_name = :dayName",
            nativeQuery = true)
    Integer selectExistProjectIdInDayOfWeek(@Param("productId") Integer productId, @Param("dayName") String dayName);
}
