package com.example.demo.configs;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class OrderConfig {
    @Bean
    CommandLineRunner commandLineRunnerOrder(OrderRepository repository) {
        return args -> {
            Order test1 = new  Order(100, new Date());

            repository.save(test1);
        };
    };
}