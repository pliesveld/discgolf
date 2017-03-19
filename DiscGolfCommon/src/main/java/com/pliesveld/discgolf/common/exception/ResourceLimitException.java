package com.pliesveld.discgolf.common.exception;

public class ResourceLimitException extends RuntimeException {
    public ResourceLimitException() {
    }

    public ResourceLimitException(String message) {
        super(message);
    }

    public ResourceLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceLimitException(Throwable cause) {
        super(cause);
    }

    public ResourceLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
