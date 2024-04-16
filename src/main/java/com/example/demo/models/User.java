package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String role;
    private String password;

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public User(String email, String role, String password) {
        this.email = email;
        this.role = role;
        this.password = hashPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
