package com.springbootproject.todoApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TodoAPIException extends RuntimeException{
    private HttpStatus status; // represents the HTTP status associated with the exception
    private String message; // represents a descriptive message associated with the exception
}
