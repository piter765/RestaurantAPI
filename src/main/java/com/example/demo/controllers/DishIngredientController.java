package com.example.demo.controllers;

import com.example.demo.models.DishIngredient;
import com.example.demo.services.DishIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dish_ingredient")
public class DishIngredientController {
    private final DishIngredientService dishIngredientService;

    @Autowired
    public DishIngredientController(DishIngredientService dishIngredientService) {
        this.dishIngredientService = dishIngredientService;
    }

    @GetMapping
    public ResponseEntity<?> getDishes() {
        try {
            List<DishIngredient> dishes = dishIngredientService.getDishes();
            return ResponseEntity.ok().body(dishes);
        } catch(IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createDish(@RequestBody DishIngredient dishIngredient) {
        try {
            dishIngredientService.createDish(dishIngredient);
            return ResponseEntity.status(HttpStatus.CREATED).body("Dish added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
