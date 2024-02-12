package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class BaseException extends RuntimeException{
    StatusCode statusCode;
    public BaseException(StatusCode statusCode){
        this.statusCode = statusCode;
    }
    public StatusCode getStatus(){
        return statusCode;
    }

}
