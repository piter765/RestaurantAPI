package com.example.demo.ingredient;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @SequenceGenerator(
            name = "ingredient_sequence",
            sequenceName = "ingredient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredient_sequence"
    )
    private Long id;
    private String name;
    private double quantity;
    private double price;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Ingredient(String name, double quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // toString method
    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
