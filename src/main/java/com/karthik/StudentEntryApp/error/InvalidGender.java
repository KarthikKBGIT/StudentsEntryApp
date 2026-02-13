package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidGender extends Exception{
    public InvalidGender(String message) {
        super(message);
    }

    public InvalidGender() {
        super();
    }

    public InvalidGender(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGender(Throwable cause) {
        super(cause);
    }

    protected InvalidGender(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
