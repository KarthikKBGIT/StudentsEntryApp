package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyExists extends Exception{
    public UsernameAlreadyExists(String message) {
        super(message);
    }

    public UsernameAlreadyExists() {
        super();
    }

    public UsernameAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected UsernameAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
