package com.nike.douye.dto;

import com.nike.douye.Enum.Code;
import com.nike.douye.exception.BaseException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {

        private Integer code;

        private T data;

    public ResponseDTO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseDTO(Integer code) {
        this.code = code;
    }

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO() {
    }

    public ResponseDTO(BaseException exception) {
        this.code = exception.getErrorCode();
        this.data = (T) exception.getMessage();
    }
}
