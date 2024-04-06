package com.example.demo.repositories;

import com.example.demo.models.Dish_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishIngredientRepository extends JpaRepository<Dish_Ingredient, Long> {
}
