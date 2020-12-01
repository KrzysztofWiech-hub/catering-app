package com.catering.repository;

import com.catering.model.DaysOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DaysOfWeekRepository extends JpaRepository<DaysOfWeek, Long> {

    @Transactional
    @Modifying
    @Query(value ="insert into cateringdb.days_of_week (days_of_week.day_name, days_of_week.product_id) values(:dayName, :productId)", nativeQuery = true)
    void insertProductToDayOfWeek(String dayName, int productId);
}
