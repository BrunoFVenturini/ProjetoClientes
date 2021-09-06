package com.projeto.projeto.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EstoqueVazioException {
    private HttpStatus httpStatus;

    private String message;

    private String details;

    public EstoqueVazioException(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
