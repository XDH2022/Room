package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Permission {
    int permissionId;
    String permissionName;
    int permissionParent;
    String category;
    String createAt;
    String updateAt;

    public Permission() {
    }

}
