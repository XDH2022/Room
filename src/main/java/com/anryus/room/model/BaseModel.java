package com.anryus.room.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    int id;
    Date createAt;
    Date updateAt;
    Date deleteAt;
}
