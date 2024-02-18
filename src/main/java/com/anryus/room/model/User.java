package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    String username;
    String password;
    String email;
    int userId;

    String roleId;
    String createAt;
    String updateAt;

    public User() {
    }

    public User(String username, String password, String roleId,String email) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.email = email;
    }
}
