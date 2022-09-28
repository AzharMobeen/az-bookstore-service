package com.az.bookstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@Getter
public class CustomRuntimeException extends RuntimeException{

    private final String message;
    private final String detail;
    private final HttpStatus httpStatus;

    public CustomRuntimeException(String message, String detail, HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
}