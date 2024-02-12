package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    String username;
    String password;
    int userId;

    String role;
    String createAt;
    String updateAt;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
