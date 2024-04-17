package com.anryus.room.model;

import com.anryus.room.common.StatusCode;
import com.anryus.room.utils.CommonExtKt;
import lombok.Data;

@Data
public class JsonVO<E> {
    int code;
    String msg;
    E data;
    long time;

    public JsonVO(int code, String msg, E data, long time) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = time;
    }

    public static <T> String success(T data) {
        return CommonExtKt.toJson(new JsonVO<T>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data, System.currentTimeMillis()));
    }

    public static <T> JsonVO<T> successByObject(T data) {
        return new JsonVO<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data, System.currentTimeMillis());
    }


    public static <T> String fail(StatusCode statusCode) {
        return CommonExtKt.toJson(new JsonVO<T>(statusCode.getCode(), statusCode.getMessage(), null, System.currentTimeMillis()));
    }

    public static <T> String fail(String msg) {
        return CommonExtKt.toJson( new JsonVO<>(StatusCode.UNKNOWN_ERROR.getCode(), msg, null, System.currentTimeMillis()));
    }
}
