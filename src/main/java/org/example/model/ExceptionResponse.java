package org.example.model;

public class ExceptionResponse {
    private String type;
    private String message;

    public ExceptionResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public ExceptionResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
