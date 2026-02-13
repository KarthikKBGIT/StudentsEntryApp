package com.karthik.StudentEntryApp.entity;

import com.karthik.StudentEntryApp.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.IgnoredPropertyException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<ErrorMessage> studentNotFoundExceptionHandler(StudentNotFound e) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(StudentIDNotFound.class)
    public ResponseEntity<ErrorMessage> studentIDNotFoundExceptionHandler(StudentIDNotFound e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(StudentNameNotFound.class)
    public ResponseEntity<ErrorMessage> studentNameNotFoundExceptionHandler(StudentNameNotFound e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentsValidationExceptionHandler(MethodArgumentNotValidException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                e.getBindingResult()
                        .getFieldErrors().stream().map((error) -> error.getDefaultMessage()).collect(Collectors.joining(",")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<ErrorMessage> usernameAlreadyExistsExceptionHandler(UsernameAlreadyExists e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ErrorMessage> emailAlreadyExistsExceptionHandler(EmailAlreadyExists e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(IgnoredPropertyException.class)
    public ResponseEntity<ErrorMessage> ignoredPropertiesExceptionHandler(IgnoredPropertyException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "The filed " + e.getPropertyName() + " is read-only and cannot be included in the request body.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
