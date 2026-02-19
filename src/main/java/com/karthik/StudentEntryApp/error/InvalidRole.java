package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRole extends Exception{
    public InvalidRole(String message) {
        super(message);
    }

    public InvalidRole() {
        super();
    }

    public InvalidRole(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRole(Throwable cause) {
        super(cause);
    }

    protected InvalidRole(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
