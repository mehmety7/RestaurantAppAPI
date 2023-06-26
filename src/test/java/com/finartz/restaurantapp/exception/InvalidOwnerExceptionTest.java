package com.finartz.restaurantapp.exception;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InvalidOwnerExceptionTest {

    private static final String USERNAME = "username";

    private static final String MESSAGE = "Invalid attempt by " + USERNAME + ". " + "There are inconsistent users for request owner and entity owner";

    @Test
    public void givenInvalidOwnerWithEmail_whenDoSomething_thenThrowInvalidOwnerExceptionWithEmail(){
        Assertions.assertEquals(MESSAGE, new InvalidOwnerException(USERNAME).getMessage());
    }


}
