package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreationRequest {
    private String customerName;
    private String email;
    private double price;
    private List<Long> dishIds;
}