package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.models.DishIngredient;
import com.example.demo.models.Ingredient;
import com.example.demo.repositories.DishRepository;
import com.example.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DishService(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public void createDish(Dish dish) {
        //TODO check if dish already exists
        dishRepository.save(dish);
    }

    public void createDishWithIngredients(String dishName, List<Long> ingredientIds, double price) {
        Dish dish = new Dish(dishName, price);
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);
        for (Ingredient ingredient : ingredients) {
            DishIngredient dishIngredient = new DishIngredient();
            dishIngredient.setIngredient(ingredient);
            dishIngredient.setDish(dish);
            // Set any other properties like quantity if necessary
            dish.getDishes_ingredients().add(dishIngredient);
        }
        dishRepository.save(dish);
    }
}
