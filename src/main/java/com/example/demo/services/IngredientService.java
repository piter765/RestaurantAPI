package com.example.demo.services;

import com.example.demo.models.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        ingredient.setCreated_at(new Date());
        ingredient.setUpdated_at(new Date());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, double quantity) {
        Optional<Ingredient> existingIngredientOptional = ingredientRepository.findById(id);
        if (existingIngredientOptional.isPresent()) {
            Ingredient existingIngredient = existingIngredientOptional.get();

            existingIngredient.setQuantity(quantity);
            existingIngredient.setUpdated_at(new Date());

            return ingredientRepository.save(existingIngredient);
        }
        return null;
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
