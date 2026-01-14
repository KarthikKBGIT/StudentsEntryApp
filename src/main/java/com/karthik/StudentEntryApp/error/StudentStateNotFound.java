package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentStateNotFound extends Exception {

    public StudentStateNotFound(String message) {
        super(message);
    }

    public StudentStateNotFound() {
        super();
    }

    public StudentStateNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentStateNotFound(Throwable cause) {
        super(cause);
    }

    protected StudentStateNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
