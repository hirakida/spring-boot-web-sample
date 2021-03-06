package com.example.web.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private long id;
    private String name;
    private String password;
    private String email;
    private List<GrantedAuthority> roles;
}
