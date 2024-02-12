package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class UserVerifyException extends BaseException{
    public UserVerifyException(){
        super(StatusCode.USER_VERIFY_ERROR);
    }
}
