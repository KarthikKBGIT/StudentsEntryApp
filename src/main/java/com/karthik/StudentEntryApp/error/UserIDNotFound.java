package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserIDNotFound extends Exception {

    public UserIDNotFound(String message) {
        super(message);
    }

    public UserIDNotFound() {
        super();
    }

    public UserIDNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIDNotFound(Throwable cause) {
        super(cause);
    }

    protected UserIDNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
