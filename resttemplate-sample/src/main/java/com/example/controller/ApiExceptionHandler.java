package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Value;

/**
 * REST APIのException Handler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * client error
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException e, WebRequest request) {
        HttpStatus status = e.getStatusCode();
        return handleExceptionInternal(e, ErrorResponse.of(status), null, status, request);
    }

    /**
     * server error
     */
    @ExceptionHandler({ HttpServerErrorException.class,
                        RestClientException.class })
    public ResponseEntity<Object> handleRestClientException(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(e, ErrorResponse.of(status), null, status, request);
    }

    /**
     * timeout
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Object> handleResourceAccessException(ResourceAccessException e, WebRequest request) {
        HttpStatus status = HttpStatus.REQUEST_TIMEOUT;
        return handleExceptionInternal(e, ErrorResponse.of(status), null, status, request);
    }

    @Value
    private static class ErrorResponse {
        private int statusCode;
        private String Message;

        public static ErrorResponse of(HttpStatus httpStatus) {
            return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
        }
    }
}
