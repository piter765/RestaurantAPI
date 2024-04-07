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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    List<Dish> dishes;

    private int price;
    private Date createdAt;

    public Order(int price, Date createdAt) {
        this.price = price;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                '}';
    }
}