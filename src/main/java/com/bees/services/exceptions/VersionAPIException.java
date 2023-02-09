package com.bees.services.exceptions;

public class VersionAPIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VersionAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public VersionAPIException(String message) {
        super(message);
    }
}
