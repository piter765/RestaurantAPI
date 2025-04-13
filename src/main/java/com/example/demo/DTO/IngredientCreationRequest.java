package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientCreationRequest {
    private String name;
    private double quantity;
    private double price;
}
