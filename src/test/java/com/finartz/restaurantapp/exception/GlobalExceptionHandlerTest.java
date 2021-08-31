package com.finartz.restaurantapp.exception;

import com.finartz.restaurantapp.model.error.ErrorMessage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;

@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Mock
    private WebRequest webRequest;

    private static final String message = "Error";

    @Test
    public void givenEntityNotFoundException_whenHandleEntityNotFoundException_thenReturnResponseEntity(){

        EntityNotFoundException exception = new EntityNotFoundException(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleEntityNotFoundException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.NOT_FOUND.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenIllegalArgumentException_whenHandleIllegalArgumentException_thenReturnResponseEntity(){

        IllegalArgumentException exception = new IllegalArgumentException(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleIllegalArgumentException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.BAD_REQUEST.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenInvalidStatusException_whenHandleInvalidStatusException_thenReturnResponseEntity(){

        InvalidStatusException exception = new InvalidStatusException(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleInvalidStatusException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.FORBIDDEN.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenMissingArgumentsException_whenHandleMissingArgumentsException_thenReturnResponseEntity(){

        MissingArgumentsException exception = new MissingArgumentsException(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleMissingArgumentsException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.BAD_REQUEST.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenInvalidCreatingException_whenHandleInvalidCreatingException_thenReturnResponseEntity(){

        InvalidCreatingException exception = new InvalidCreatingException(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleInvalidCreatingException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.NOT_ACCEPTABLE.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenInvalidOwnerException_whenHandleInvalidOwnerException_thenReturnResponseEntity(){

        InvalidOwnerException exception = new InvalidOwnerException();

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleInvalidOwnerException(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.FORBIDDEN.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), "There are inconsistent users for request owner and entity owner");
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Test
    public void givenException_whenHandleException_thenReturnResponseEntity(){

        Exception exception = new Exception(message);

        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.globalExceptionHandler(exception, webRequest);

        Assertions.assertEquals(responseEntity.getBody().getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        Assertions.assertEquals(responseEntity.getBody().getMessage(), message);
        Assertions.assertNotNull(responseEntity.getBody().getTimestamp());
        Assertions.assertNull(responseEntity.getBody().getDescription());

    }

    @Bean
    private GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }


}
