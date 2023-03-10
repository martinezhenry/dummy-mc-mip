package com.hvs.dummy.mip.exceptions;

public class NotFoundConnectionsException extends Exception {
    public NotFoundConnectionsException() {
    }

    public NotFoundConnectionsException(String message) {
        super(message);
    }

    public NotFoundConnectionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundConnectionsException(Throwable cause) {
        super(cause);
    }

    public NotFoundConnectionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
