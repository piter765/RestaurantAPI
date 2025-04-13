package com.example.demo.services;

import com.example.demo.DTO.DishOrderStats;
import com.example.demo.models.Dish;
import com.example.demo.models.DishIngredient;
import com.example.demo.models.Ingredient;
import com.example.demo.repositories.DishIngredientRepository;
import com.example.demo.repositories.DishRepository;
import com.example.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final DishIngredientRepository dishIngredientRepository;

    @Autowired
    public DishService(DishRepository dishRepository, IngredientRepository ingredientRepository, DishIngredientRepository dishIngredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishIngredientRepository = dishIngredientRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public void createDish(Dish dish) {
        //TODO check if dish already exists
        dishRepository.save(dish);
    }

<<<<<<< Updated upstream
    public void createDishWithIngredients(String dishName, List<Long> ingredientIds, double price, int[] quantities) {
=======
    public void createDishWithIngredients(String dishName, List<Long> ingredientIds, List<Integer> quantities, double price) {
>>>>>>> Stashed changes
        Dish dish = new Dish(dishName, price);

        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);
<<<<<<< Updated upstream
=======

        if (ingredients.size() != quantities.size()) {
            throw new IllegalArgumentException("Number of ingredients does not match number of quantities.");
        }

>>>>>>> Stashed changes
        int i = 0;
        for (Ingredient ingredient : ingredients) {
            DishIngredient dishIngredient = new DishIngredient();

            dishIngredient.setIngredient(ingredient);
            dishIngredient.setDish(dish);
<<<<<<< Updated upstream
            dishIngredient.setQuantity(quantities[i]);
=======
            dishIngredient.setQuantity(quantities.get(i++));
>>>>>>> Stashed changes

            dish.getDishes_ingredients().add(dishIngredient); // Add DishIngredient to Dish
            ingredient.getDishes_ingredients().add(dishIngredient); // Add DishIngredient to Ingredient

            dishIngredientRepository.save(dishIngredient);
            i++;
        }
        dishRepository.save(dish);
    }

    public List<DishOrderStats> getMostOrderedDishes() {
        List<Object[]> results = dishRepository.findMostOrderedDishes();
        return results.stream()
                .map(row -> new DishOrderStats((String) row[0], ((Number) row[1]).longValue()))
                .toList();
    }
}
