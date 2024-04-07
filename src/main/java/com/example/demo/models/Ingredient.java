package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double quantity;
    private double price;
    private Date created_at;
    private Date updated_at;

    @OneToMany(mappedBy = "ingredient")
    List<DishIngredient> dishes_ingredients = new ArrayList<>();

    public Ingredient(String name, double quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                '}';
    }
}
