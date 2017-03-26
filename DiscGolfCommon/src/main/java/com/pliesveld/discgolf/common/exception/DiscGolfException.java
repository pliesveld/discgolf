package com.pliesveld.discgolf.common.exception;

public class DiscGolfException extends RuntimeException {
    public DiscGolfException() {
        super();
    }

    public DiscGolfException(String message) {
        super(message);
    }

    public DiscGolfException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscGolfException(Throwable cause) {
        super(cause);
    }

    protected DiscGolfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
