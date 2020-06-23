package com.andreibarroso.springionic.exceptions;

public class DateIntegrityException extends  RuntimeException {

    public DateIntegrityException(String msg) {
        super(msg);

    }

    public DateIntegrityException(String msg, Throwable cause) {
        super(msg, cause);

    }
}
