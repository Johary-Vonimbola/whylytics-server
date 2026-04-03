package com.example.whylytics.middleware;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.whylytics.utils.Response;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleAll(Exception ex){
        return ResponseEntity.status(500).body(new Response<>(false, "Server error", null, new String[] { ex.getMessage() }));
    }

}
