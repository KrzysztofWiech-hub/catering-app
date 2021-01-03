package com.catering.repository;

import com.catering.model.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaysOfWeekRepository extends JpaRepository<DayOfWeek, Long> {

    @Modifying
    @Query(value = "INSERT INTO cateringdb.day_of_week (day_of_week.day_name, day_of_week.product_id) VALUES(:dayName, :productId)",
            nativeQuery = true)
    void insertProductToDayOfWeek(String dayName, int productId);

    boolean existsProductById(Integer productId);

    @Modifying
    @Query(value = "DELETE DAY_OF_WEEK FROM cateringdb.day_of_week DAY_OF_WEEK WHERE DAY_OF_WEEK.product_id = :productId",
            nativeQuery = true)
    void deleteProductFromDaysOfWeek(int productId);

    @Modifying
    @Query(value = "DELETE DAY_OF_WEEK FROM cateringdb.day_of_week DAY_OF_WEEK WHERE DAY_OF_WEEK.product_id = :productId" +
            " AND DAY_OF_WEEK.day_name= :dayName",
            nativeQuery = true)
    void deleteProductFromDaysOfWeekByProductIdAndDayName(int productId, String dayName);

    @Query(value = "SELECT DAY_OF_WEEK.product_id AS productId " +
            "FROM cateringdb.day_of_week AS DAY_OF_WEEK " +
            "WHERE DAY_OF_WEEK.day_name= :dayName"
            , nativeQuery = true)
    List<Integer> selectAllProductsIdOfDayByDayName(String dayName);

}
