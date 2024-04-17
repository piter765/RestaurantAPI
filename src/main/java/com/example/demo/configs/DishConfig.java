//package com.example.demo.configs;
//
//import com.example.demo.models.Dish;
//import com.example.demo.models.DishIngredient;
//import com.example.demo.models.Ingredient;
//import com.example.demo.repositories.DishIngredientRepository;
//import com.example.demo.repositories.DishRepository;
//import com.example.demo.repositories.IngredientRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class DishConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunnerDishAndIngredient(IngredientRepository ingredientRepository, DishRepository dishRepository, DishIngredientRepository dishIngredientRepository) {
//        return args -> {
//            Ingredient ingredient1 = new Ingredient(
//                    "test2",
//                    100,
//                    2000
//            );
//
//            //ingredientRepository.save(ingredient1);
//
//            Dish dish1 = new Dish(
//                    "pizza",
//                    50
//            );
//
//            DishIngredient dishIngredient = new DishIngredient();
//            dishIngredient.setIngredient(ingredient1);
//            dishIngredient.setDish(dish1);
//            dishIngredient.setQuantity(1); // Set quantity as per your requirement
//
//            List<DishIngredient> dishIngredients = new ArrayList<>();
//            dishIngredients.add(dishIngredient);
//
//            dish1.setDishes_ingredients(dishIngredients);
//            ingredient1.setDishes_ingredients(dishIngredients);
//
//            dishIngredientRepository.save(dishIngredient);
//        };
//    };
//}
//
