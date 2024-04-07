package com.example.demo.services;

import com.example.demo.models.DishIngredient;
import com.example.demo.repositories.DishIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishIngredientService {
    private final DishIngredientRepository dishIngredientRepository;

    @Autowired
    public DishIngredientService(DishIngredientRepository dishIngredientRepository) {
        this.dishIngredientRepository = dishIngredientRepository;
    }

    public List<DishIngredient> getDishes() {
        return dishIngredientRepository.findAll();
    }

    public void createDish(DishIngredient dishIngredient) {
        dishIngredientRepository.save(dishIngredient);
    }
}
