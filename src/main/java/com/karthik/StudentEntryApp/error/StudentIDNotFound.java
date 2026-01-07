package com.karthik.StudentEntryApp.error;

public class StudentIDNotFound extends Exception {

    public StudentIDNotFound(String message) {
        super(message);
    }

    public StudentIDNotFound() {
        super();
    }

    public StudentIDNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentIDNotFound(Throwable cause) {
        super(cause);
    }

    protected StudentIDNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
