package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class NotHavePermissionException extends BaseException {
    public NotHavePermissionException() {
        super(StatusCode.NOT_PERMISSION);
    }
}
