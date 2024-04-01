package com.example.demo.ingredient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IngredientConfig {
    @Bean
    CommandLineRunner commandLineRunner(IngredientRepository repository) {
        return args -> {
            Ingredient test1 = new Ingredient(
                    "test1",
                    100,
                    2000
            );

            repository.save(test1);
        };
    };
}

