package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseModel{
    String roleName;
    String roleDescription;

    public Role() {
    }
}
