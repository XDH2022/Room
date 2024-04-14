package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Friend extends BaseModel{
    int owner_id;
    int friend_id;
    String username;
}
