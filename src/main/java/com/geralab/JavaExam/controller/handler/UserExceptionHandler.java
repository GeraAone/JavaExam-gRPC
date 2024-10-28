package com.geralab.JavaExam.controller.handler;

import com.geralab.JavaExam.exception.ApiError;
import com.geralab.JavaExam.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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
                new ApiError(HttpStatus.NOT_FOUND.value() ,ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleClientErrors(HttpClientErrorException ex) {
        // Логирование ошибки
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value() ,ex.getMessage() + ": Внешний сервис вернул ошибку клиента"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleWrongRoleErrors(AccessDeniedException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage() + ": У вас нет прав для данного действия"),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ApiError> handleServerErrors(HttpServerErrorException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.SERVICE_UNAVAILABLE.value() ,ex.getMessage() + ": Ошибка на стороне внешнего сервиса"),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiError> handleNetworkErrors(ResourceAccessException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.GATEWAY_TIMEOUT.value(), ex.getMessage() + ":Ошибка на стороне внешнего сервиса: "),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAuthErrors(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.BAD_REQUEST.value(),ex.getMessage() + "Пользователь с указанным именем уже существует"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleAuthErrors(BadCredentialsException ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage() + ": Неправильный логин или пароль"),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
