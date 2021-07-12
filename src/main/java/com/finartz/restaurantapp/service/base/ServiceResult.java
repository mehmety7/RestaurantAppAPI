package com.finartz.restaurantapp.service.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceResult<T> {

    private T result;

    private HttpStatus httpStatus = HttpStatus.OK;

    public ServiceResult(T result) {
        this.result = result;
    }

    public ServiceResult(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ServiceResult(T result, HttpStatus httpStatus) {
        this.result = result;
        this.httpStatus = httpStatus;
    }

}
