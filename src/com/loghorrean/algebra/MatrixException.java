package com.loghorrean.algebra;

public class MatrixException extends Throwable {
    private String message;

    public MatrixException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
