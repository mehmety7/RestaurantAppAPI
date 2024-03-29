package com.finartz.restaurantapp.exception;

import java.util.Objects;


public class InvalidOwnerException extends RuntimeException{

    private final String requestOwnerEmail;

    public InvalidOwnerException(String requestOwnerEmail){
        this.requestOwnerEmail = requestOwnerEmail;
    }

    @Override
    public String getMessage(){

        String message = "";

        if(Objects.nonNull(requestOwnerEmail)){
            message = "Invalid attempt by " + requestOwnerEmail + ". ";
        }
            message = message + "There are inconsistent users for request owner and entity owner";

        return message;
    }

}
