package com.finartz.restaurantapp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class InvalidOwnerException extends RuntimeException{

    private String requestOwnerEmail;

    public InvalidOwnerException(String requestOwnerEmail){
        this.requestOwnerEmail = requestOwnerEmail;
    }

    @Override
    public String getMessage(){
        String message;

        if(Objects.nonNull(requestOwnerEmail)){
            message = "Invalid attempt by " + requestOwnerEmail +
                    ". Inconsistent users for request owner and entity owner";
        }else{
            message = "Inconsistent users for request owner and entity owner";
        }

        return message;
    }

}
