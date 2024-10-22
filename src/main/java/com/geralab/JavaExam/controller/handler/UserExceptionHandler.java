package com.geralab.JavaExam.controller.handler;

import com.geralab.JavaExam.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleClientErrors(HttpClientErrorException ex) {
        // Логирование ошибки
        return new ResponseEntity<>("Внешний сервис вернул ошибку клиента: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleServerErrors(HttpServerErrorException ex) {
        // Логирование ошибки
        return new ResponseEntity<>("Ошибка на стороне внешнего сервиса: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleNetworkErrors(ResourceAccessException ex) {
        // Логирование ошибки
        return new ResponseEntity<>("Ошибка сети или таймаут при обращении к внешнему сервису: " + ex.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }
}
