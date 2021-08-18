package com.finartz.restaurantapp.exception;

public class InvalidStatusException extends RuntimeException{

    public InvalidStatusException(String message){
        super(message);
    }

}
