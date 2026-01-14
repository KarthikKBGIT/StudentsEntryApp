package com.karthik.StudentEntryApp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentDepartmentNotFound extends Exception {

    public StudentDepartmentNotFound(String message) {
        super(message);
    }

    public StudentDepartmentNotFound() {
        super();
    }

    public StudentDepartmentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentDepartmentNotFound(Throwable cause) {
        super(cause);
    }

    protected StudentDepartmentNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
