package com.example.demo.configs;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunnerCustomer(CustomerRepository repository) {
        return args -> {
            Customer test1 = new  Customer("testCustomer1", "test1@gmail.com");

            repository.save(test1);
        };
    };
}

