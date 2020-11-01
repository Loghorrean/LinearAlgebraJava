package com.loghorrean.algebra;

public class FractionException extends Throwable {
    private String message;
    public FractionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
