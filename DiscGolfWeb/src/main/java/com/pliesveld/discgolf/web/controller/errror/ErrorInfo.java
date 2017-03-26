package com.pliesveld.discgolf.web.controller.errror;

public class ErrorInfo {
    private final String requestURI;
    private final String message;

    public ErrorInfo(String requestURI, String message) {
        this.requestURI = requestURI;
        this.message = message;
    }

    public String getRequestURI() { return requestURI; }

    public String getMessage() { return message; }
}
