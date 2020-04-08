package com.nike.douye.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    Integer errorCode;
    String message;

    public BaseException(String message, Integer errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BaseException() {
    }
}
