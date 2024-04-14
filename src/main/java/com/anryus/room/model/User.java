package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel{
    String username;
    String password;
    String email;
    String roleId;

    public User() {
    }

    public User(String username, String password, String roleId,String email) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.email = email;
    }
}
