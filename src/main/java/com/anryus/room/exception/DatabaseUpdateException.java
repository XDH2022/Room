package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;
import lombok.Getter;

@Getter
public class DatabaseUpdateException extends BaseException{
    String origin;
    public DatabaseUpdateException(String origin) {
        super(StatusCode.DATABASE_UPDATE_ERROR);
        this.origin = origin;
    }

}
