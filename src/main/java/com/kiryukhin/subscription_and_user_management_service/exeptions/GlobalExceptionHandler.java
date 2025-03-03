package com.kiryukhin.subscription_and_user_management_service.exeptions;

import org.apache.coyote.BadRequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "User not found", "UserNotFoundException", ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "User already exists", "UserAlreadyExistsException", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Invalid input data");
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Validation error", "ValidationException", errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Data integrity violation", "DataIntegrityViolationException", "Email already exists");
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Bad request", "BadRequestException", ex.getMessage());
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleSubscriptionNotFoundException(SubscriptionNotFoundException ex) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Subscription not found", "SubscriptionNotFoundException", ex.getMessage());
    }

    @ExceptionHandler(SubscriptionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleSubscriptionAlreadyExistsException(SubscriptionAlreadyExistsException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, "Subscription already exists", "SubscriptionAlreadyExistsException", ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Invalid input format", "HttpMessageNotReadableException", "Invalid value provided in request body");
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String errorType, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setProperty("error", errorType);
        return problemDetail;
    }

}
