package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public void createDish(Dish dish) {
        //TODO check if dish already exists
        dishRepository.save(dish);
    }
}
