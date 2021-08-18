package com.finartz.restaurantapp.exception;

public class MissingArgumentsException extends RuntimeException{

    public MissingArgumentsException(String message){
        super(message);
    }

}
