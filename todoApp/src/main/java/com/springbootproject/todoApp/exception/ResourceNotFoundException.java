package com.springbootproject.todoApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// when an instance of ResourceNotFoundException is thrown, the HTTP response status should be set to 404 Not Found
// Extending RuntimeException makes it an unchecked exception, unchecked exceptions are typically used for unexpected errors
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    // allows to provide a custom error message when throwing this exception
    public ResourceNotFoundException(String message){
        super(message);
    }
}
