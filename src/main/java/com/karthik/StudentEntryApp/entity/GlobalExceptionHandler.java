package com.karthik.StudentEntryApp.entity;

import com.karthik.StudentEntryApp.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentIDNotFound.class)
    public ResponseEntity<ErrorMessage> studentIDNotFoundExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(StudentNameNotFound.class)
    public ResponseEntity<ErrorMessage> studentNameNotFoundExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(StudentDepartmentNotFound.class)
    public ResponseEntity<ErrorMessage> studentDepartmentNotFoundExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(StudentStateNotFound.class)
    public ResponseEntity<ErrorMessage> studentStateNotFoundExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentsValidationExceptionHandler(MethodArgumentNotValidException e){
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                e.getBindingResult()
                .getFieldErrors().stream().map((error) -> error.getDefaultMessage()).collect(Collectors.joining(",")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<ErrorMessage> usernameAlreadyExistsExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ErrorMessage> emailAlreadyExistsExceptionHandler(Exception e){
        ErrorMessage message = new ErrorMessage(HttpStatus.NO_CONTENT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

}
