package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishCreationRequest {
    private String dishName;
    private List<Long> ingredientIds;
    private double price;
}