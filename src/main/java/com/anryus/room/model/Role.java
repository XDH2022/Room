package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Role {
    int roleId;
    String roleName;
    String roleDescription;
    String createAt;
    String updateAt;

    public Role() {
    }
}
