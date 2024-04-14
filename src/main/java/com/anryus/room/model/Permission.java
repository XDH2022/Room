package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
public class Permission extends BaseModel {
    String permissionName;
    int permissionParent;
    String category;

    public Permission() {
    }

}
