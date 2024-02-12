package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class UserAlreadyExistException extends BaseException{
    public UserAlreadyExistException() {
        super(StatusCode.USER_ALREADY_EXIST);
    }
}
