package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class CheckPermissionException extends BaseException{

    public CheckPermissionException() {
        super(StatusCode.CHECK_PERMISSION_ERROR);
    }
}
