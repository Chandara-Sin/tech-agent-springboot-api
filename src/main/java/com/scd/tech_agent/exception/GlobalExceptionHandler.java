package com.scd.tech_agent.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.scd.tech_agent.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFound ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), ex.getMessage(),
                request.getRequestURI(), HttpStatus.NOT_FOUND.getReasonPhrase()
        );
        return new ResponseEntity<>(
                ResponseModel.errorResponse(errorDetails, HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataInvalid.class)
    public ResponseEntity<Object> dataDuplicateException(DataInvalid ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), ex.getMessage(),
                request.getRequestURI(), HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
        return new ResponseEntity<>(
                ResponseModel.errorResponse(errorDetails, HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(), ex.getMessage(),
                request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );
        return new ResponseEntity<>(
                ResponseModel.errorResponse(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
