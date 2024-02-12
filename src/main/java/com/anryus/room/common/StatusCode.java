package com.anryus.room.common;

import lombok.Getter;

@Getter
public enum StatusCode {
    DATABASE_UPDATE_ERROR(0,"数据更新错误"),

    SUCCESS(1,"成功"),


    NOT_FOUND_USER(40001,"未找到用户"),
    USER_ALREADY_EXIST(40002,"用户已经存在"),
    USER_VERIFY_ERROR(40003,"用户验证失败"),
    NOT_LOGIN(40004,"未登录"),

    CHECK_PERMISSION_ERROR(30001,"权限检查失败"),
    NOT_PERMISSION(30002,"无权访问"),

    NOT_FOUNT_ROOM(20001,"未找到聊天室"),
    ALREADY_IN_ROOM(20002,"已经在聊天室中了" ),

    UNKNOWN_ERROR(50000,"未知错误");


    private final int code;
    private final String message;

    StatusCode(int code,String message) {
        this.code = code;
        this.message = message;
    }

}