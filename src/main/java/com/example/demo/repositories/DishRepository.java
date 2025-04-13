package com.example.demo.repositories;

import com.example.demo.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = """
        SELECT d.name AS name, COUNT(od.order_id) AS timesOrdered
        FROM orders_dishes od
        JOIN dishes d ON od.dish_id = d.id
        GROUP BY d.id, d.name
        ORDER BY timesOrdered DESC
        """, nativeQuery = true)
    List<Object[]> findMostOrderedDishes();
}
