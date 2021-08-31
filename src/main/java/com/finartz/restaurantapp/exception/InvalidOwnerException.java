package com.finartz.restaurantapp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class InvalidOwnerException extends RuntimeException{

    private String requestOwnerEmail;
    private String message;


    public InvalidOwnerException(String requestOwnerEmail){
        this.requestOwnerEmail = requestOwnerEmail;
    }

    @Override
    public String getMessage(){

        if(Objects.nonNull(requestOwnerEmail)){
            message = "Invalid attempt by " + requestOwnerEmail + ". ";
        }
            message = message + "There are inconsistent users for request owner and entity owner";

        return message;
    }

}
