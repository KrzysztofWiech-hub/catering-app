package com.catering.repository;

import com.catering.model.DaysOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysOfWeekRepository extends JpaRepository<DaysOfWeek, Long> {

    @Modifying
    @Query(value = "INSERT INTO cateringdb.days_of_week (days_of_week.day_name, days_of_week.product_id) VALUES(:dayName, :productId)",
            nativeQuery = true)
    void insertProductToDayOfWeek(String dayName, int productId);

    boolean existsProductById(Integer productId);

    @Modifying
    @Query(value = "DELETE DAYS_OF_WEEK FROM cateringdb.days_of_week DAYS_OF_WEEK WHERE DAYS_OF_WEEK.product_id = :productId",
            nativeQuery = true)
    void deleteProductFromDaysOfWeek(int productId);

    @Modifying
    @Query(value = "DELETE DAYS_OF_WEEK FROM cateringdb.days_of_week DAYS_OF_WEEK WHERE DAYS_OF_WEEK.product_id = :productId AND DAYS_OF_WEEK.day_name= :dayName",
            nativeQuery = true)
    void deleteProductFromDaysOfWeekByProductIdAndDayName(int productId, String dayName);

}
