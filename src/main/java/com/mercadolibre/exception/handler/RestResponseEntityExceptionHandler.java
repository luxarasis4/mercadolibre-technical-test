
package com.mercadolibre.exception.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mercadolibre.exception.CanNotBuyNothingException;
import com.mercadolibre.rest.client.dto.ErrorResponse;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ResponseStatus(
            value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(
            value = {
                NoHandlerFoundException.class, CanNotBuyNothingException.class
            })
    public ResponseEntity<ErrorResponse> handleNotFound(HttpServletRequest request, Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(HttpStatus.NOT_FOUND.name());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setErrors(Collections.singletonList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            value = {
                MethodArgumentNotValidException.class
            })
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            errors.add(((FieldError) error).getField() + " - " + error.getDefaultMessage());
        });

        ErrorResponse response = new ErrorResponse();
        response.setMessage(HttpStatus.BAD_REQUEST.name());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setErrors(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(
            value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
            value = {
                Exception.class
            })
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrors(Collections.singletonList(e.getMessage()));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
