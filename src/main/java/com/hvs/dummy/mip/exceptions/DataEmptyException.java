package com.hvs.dummy.mip.exceptions;

public class DataEmptyException extends Exception {
    public DataEmptyException() {
    }

    public DataEmptyException(String message) {
        super(message);
    }

    public DataEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataEmptyException(Throwable cause) {
        super(cause);
    }

    public DataEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
