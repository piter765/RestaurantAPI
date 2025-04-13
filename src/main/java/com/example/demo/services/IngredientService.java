package com.example.demo.services;

import com.example.demo.DTO.IngredientCreationRequest;
import com.example.demo.models.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, JdbcTemplate jdbcTemplate) {
        this.ingredientRepository = ingredientRepository;
        this.jdbcTemplate = jdbcTemplate;
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

    @Transactional
    public void createRestockProcedureIfNotExists() {
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS RestockLowIngredients");

        jdbcTemplate.execute("""
        CREATE PROCEDURE RestockLowIngredients()
        BEGIN
            DECLARE done INT DEFAULT 0;
            DECLARE ing_id BIGINT;
            DECLARE ing_quantity INT;
            
            DECLARE cur CURSOR FOR 
                SELECT id, quantity FROM ingredients;
            
            DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
            
            OPEN cur;
            
            read_loop: LOOP
                FETCH cur INTO ing_id, ing_quantity;
                IF done THEN
                    LEAVE read_loop;
                END IF;
                
                IF ing_quantity < 50 THEN
                    UPDATE ingredients
                    SET quantity = 100
                    WHERE id = ing_id;
                END IF;
            END LOOP;
            
            CLOSE cur;
        END
        """);

        System.out.println("Procedure created!");
    }

    @Transactional
    public void restockLowIngredientsWithCursor() {
        // Wywołanie procedury
        jdbcTemplate.execute("CALL RestockLowIngredients()");
        System.out.println("Procedure RestockLowIngredients executed.");
    }
}
