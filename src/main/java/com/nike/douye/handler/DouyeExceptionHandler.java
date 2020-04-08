package com.nike.douye.handler;

import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class DouyeExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseDTO exceptionHandle(RuntimeException exception){
        if(exception instanceof BaseException){
            log.error("自定义异常： ",exception);
            BaseException baseException = (BaseException) exception;
            return new ResponseDTO(baseException.getErrorCode(),baseException.getMessage());
        }else {
            log.error("未知异常：",exception);
            return new ResponseDTO(exception);
        }
    }
}
