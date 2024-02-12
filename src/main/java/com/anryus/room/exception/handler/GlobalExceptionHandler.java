package com.anryus.room.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.anryus.room.common.StatusCode;
import com.anryus.room.exception.*;
import com.anryus.room.model.JsonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public String userAlreadyExistExceptionHandler(UserAlreadyExistException e){
        logger.error(e.getClass().getSimpleName()+":"+e.getMessage());
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = NotFoundUserException.class)
    public String notFoundUserHandler(NotFoundUserException e){
        logger.error(e.getClass().getSimpleName()+":"+e.getMessage());
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = UserVerifyException.class)
    public String userVerifyHandler(UserVerifyException e){
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = CheckPermissionException.class)
    public String checkPermissionHandler(CheckPermissionException e){
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = NotPermissionException.class)
    public String notPermissionHandler(NotPermissionException e){
        logger.error(e.getMessage());
        return JsonVO.fail(StatusCode.NOT_PERMISSION);
    }

    @ExceptionHandler(value = DatabaseUpdateException.class)
    public String databaseUpdateHandler(DatabaseUpdateException e){
        logger.error(e.getOrigin()+":"+"数据库更新失败");
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = NotHavePermissionException.class)
    public String notPermissionExceptionHandler(NotHavePermissionException e){
        return JsonVO.fail(e.getStatus());
    }

    @ExceptionHandler(value = NotLoginException.class)
    public String notLoginExceptionHandler(NotLoginException e){
        return JsonVO.fail(StatusCode.NOT_LOGIN);
    }

    @ExceptionHandler(value = BaseException.class)
    public String exceptionHandler(BaseException e){
        logger.error(e.getClass().getSimpleName()+":"+ Arrays.toString(e.getStackTrace()));
        e.printStackTrace();
        return JsonVO.fail(e.getStatus());
    }
}
