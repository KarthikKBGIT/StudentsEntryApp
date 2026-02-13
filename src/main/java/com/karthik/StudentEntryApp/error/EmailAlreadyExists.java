package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExists extends Exception {
    public EmailAlreadyExists(String message) {
        super(message);
    }

    public EmailAlreadyExists() {
        super();
    }

    public EmailAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected EmailAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
