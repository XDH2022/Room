package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class NotFoundUserException extends BaseException{

    public NotFoundUserException() {
        super(StatusCode.NOT_FOUND_USER);
    }
}
