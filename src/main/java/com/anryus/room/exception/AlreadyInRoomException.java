package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class AlreadyInRoomException extends BaseException {
    public AlreadyInRoomException() {
        super(StatusCode.ALREADY_IN_ROOM);
    }
}
