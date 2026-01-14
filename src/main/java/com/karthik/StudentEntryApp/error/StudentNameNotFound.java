package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNameNotFound extends Exception {

    public StudentNameNotFound(String message) {
        super(message);
    }

    public StudentNameNotFound() {
        super();
    }

    public StudentNameNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNameNotFound(Throwable cause) {
        super(cause);
    }

    protected StudentNameNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
