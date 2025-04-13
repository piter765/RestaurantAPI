package com.example.demo.services;

import com.example.demo.DTO.OrderCreationRequest;
import com.example.demo.models.*;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.DishRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository ingredientRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, DishRepository dishRepository, CustomerRepository customerRepository){
        this.orderRepository = orderRepository;
        this.ingredientRepository = dishRepository;
        this.customerRepository = customerRepository;
    }

//    public List<Order> getOrders(){
//        return orderRepository.findAll();
//    }

    public List<Order> getOrders(String from, String to) throws Exception{
        System.out.println("przed ifem");
        System.out.println(from + "  " +  to + " dddd");
        System.out.println((from instanceof String) + " type of");
        System.out.println(from == null + " from == null");
//        if (from.getClass().equals(String.class)) {
//            System.out.println(" w ifie");
            return orderRepository.findAll();
       // }
//        System.out.println("po ifie");
//        Date fromDate = parseDate(from);
//        Date toDate = parseDate(to);
//        return orderRepository.findByDateBetween(fromDate, toDate);
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return dateFormat.parse(dateStr);
    }

    private boolean isEmptyOrInvalidDate(String dateStr) {
        return dateStr == null || dateStr.isEmpty();
    }

    public void createOrder(OrderCreationRequest request) {
        Order order = new Order();
        List<Dish> dishes = ingredientRepository.findAllById(request.getDishIds());

        Customer customer = new Customer(request.getCustomerName(), request.getEmail());
        customerRepository.save(customer);

        double fullPrice = 0;
        for (Dish dish : dishes) {
            fullPrice += dish.getPrice();
        }
        order.setPrice(fullPrice);

        order.setCustomer(customer);
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
