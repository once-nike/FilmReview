package com.nike.douye.handler;

import com.nike.douye.Enum.Code;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class DouyeExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO exceptionHandle(Exception exception){
        if(exception instanceof BaseException){
            log.error("自定义异常： ",exception);
            BaseException baseException = (BaseException) exception;
            return new ResponseDTO(baseException.getErrorCode(),baseException.getMessage());
        }else if (exception instanceof MethodArgumentNotValidException){
            log.error("参数校验异常： ",exception);
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) exception;
            return new ResponseDTO(Code.PARAM_ERROR.getValue(),manve.getMessage());
        }else{
            log.error("未知异常：",exception);
            return new ResponseDTO(exception);
        }
    }
}
