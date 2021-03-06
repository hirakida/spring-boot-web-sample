package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int id;
    private String name;
    private LocalDateTime createdAt;

    public User(String name) {
        this.name = name;
        createdAt = LocalDateTime.now();
    }
}
