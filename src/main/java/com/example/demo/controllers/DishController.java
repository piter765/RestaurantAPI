package com.example.demo.controllers;

import com.example.demo.DTO.DishCreationRequest;
import com.example.demo.DTO.DishOrderStats;
import com.example.demo.models.Dish;
import com.example.demo.models.DishIngredient;
import com.example.demo.models.Ingredient;
import com.example.demo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dishes")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<?> getDishes() {
        try {
            List<Dish> dishes = dishService.getDishes();
            return ResponseEntity.ok().body(dishes);
        } catch(IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping
//    public ResponseEntity<String> createDish(@RequestBody Dish dish) {
//        try {
//            dishService.createDish(dish);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Dish added successfully");
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }

    @PostMapping
    public ResponseEntity<String> createDishWithIngredients(@RequestBody DishCreationRequest request) {
        try {
            dishService.createDishWithIngredients(request.getDishName(), request.getIngredientIds(), request.getPrice(), request.getQuantities());
            return ResponseEntity.status(HttpStatus.CREATED).body("Dish added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating dish: " + e.getMessage());
        }
    }

    @GetMapping("/most-ordered")
    public ResponseEntity<List<DishOrderStats>> getMostOrderedDishes() {
        return ResponseEntity.ok(dishService.getMostOrderedDishes());
    }

}
