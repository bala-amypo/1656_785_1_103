package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntime(RuntimeException ex) {

        String msg = ex.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (msg != null) {
            if (msg.contains("exists")) {
                status = HttpStatus.BAD_REQUEST;
            } else if (msg.contains("after")) {
                status = HttpStatus.BAD_REQUEST;
            } else if (msg.contains("not found")) {
                status = HttpStatus.NOT_FOUND;
            } else if (msg.contains("must") || msg.contains("invalid")) {
                status = HttpStatus.BAD_REQUEST;
            }
        }

        return ResponseEntity
                .status(status)
                .body(new ApiErrorResponse(status.value(), msg));
    }
}
