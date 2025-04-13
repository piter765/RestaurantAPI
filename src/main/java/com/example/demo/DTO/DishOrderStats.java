package com.example.demo.DTO;

import lombok.Data;

@Data
public class DishOrderStats {
    private String name;
    private Long timesOrdered;

    public DishOrderStats(String name, Long timesOrdered) {
        this.name = name;
        this.timesOrdered = timesOrdered;
    }

}