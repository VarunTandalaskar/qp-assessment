package com.grocery.booking.exception;

import com.grocery.booking.constants.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(AppConstants.TIMESTAMP, LocalDateTime.now());
        errorResponse.put(AppConstants.STATUS, HttpStatus.NOT_FOUND.value());
        errorResponse.put(AppConstants.ERROR, AppConstants.NOT_FOUND);
        errorResponse.put(AppConstants.MESSAGE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle InsufficientStockException
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStockException(InsufficientStockException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(AppConstants.TIMESTAMP, LocalDateTime.now());
        errorResponse.put(AppConstants.STATUS, HttpStatus.BAD_REQUEST.value());
        errorResponse.put(AppConstants.ERROR, AppConstants.BAD_REQUEST);
        errorResponse.put(AppConstants.MESSAGE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(AppConstants.TIMESTAMP, LocalDateTime.now());
        errorResponse.put(AppConstants.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put(AppConstants.ERROR, AppConstants.INTERNAL_SERVER_ERROR);
        errorResponse.put(AppConstants.MESSAGE, "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
