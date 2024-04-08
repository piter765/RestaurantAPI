package com.example.demo.services;

import com.example.demo.DTO.OrderCreationRequest;
import com.example.demo.models.*;
import com.example.demo.repositories.DishRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository ingredientRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, DishRepository dishRepository){
        this.orderRepository = orderRepository;
        this.ingredientRepository = dishRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void createOrder(OrderCreationRequest request) {
        Order order = new Order(request.getCustomerName(), request.getEmail());
        List<Dish> dishes = ingredientRepository.findAllById(request.getDishIds());

        double fullPrice = 0;
        for (Dish dish : dishes) {
            fullPrice += dish.getPrice();
        }

        order.setPrice(fullPrice);

        order.setDishes(dishes);
        orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            existingOrder.setCustomer(updatedOrder.getCustomer());
            existingOrder.setPrice(updatedOrder.getPrice());

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
