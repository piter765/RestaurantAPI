package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
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
