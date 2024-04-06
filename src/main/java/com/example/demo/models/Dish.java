package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

//    @ManyToMany(mappedBy = "dishes", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    //private List<Ingredient> ingredients = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "dish")
    List<Dish_Ingredient> dishes_ingredients;

    @ManyToMany
    List<Order> orders;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}