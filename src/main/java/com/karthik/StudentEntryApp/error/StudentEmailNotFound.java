package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentEmailNotFound extends Exception {
    public StudentEmailNotFound(String message) {
        super(message);
    }

    public StudentEmailNotFound() {
        super();
    }

    public StudentEmailNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentEmailNotFound(Throwable cause) {
        super(cause);
    }

    protected StudentEmailNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
