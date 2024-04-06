package com.example.demo.controllers;

import com.example.demo.models.Ingredient;
import com.example.demo.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<?>  getIngredients() {
        try {
            List<Ingredient> ingredients = ingredientService.getIngredients();
            return ResponseEntity.ok().body(ingredients);
        } catch(IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient ingredient) {
        try {
            Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIngredient);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {
        try {
            Ingredient ingredient = ingredientService.updateIngredient(id, updatedIngredient);
            if (ingredient != null) {
                return ResponseEntity.ok().body(ingredient);
            } else {
                return new ResponseEntity<>("Ingredient not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        try {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
