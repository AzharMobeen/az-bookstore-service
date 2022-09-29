package com.az.bookstore.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.az.bookstore.constant.Constants.*;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomRuntimeException.class)
    public ResponseEntity<ErrorMessage> handleCustomRuntimeException(CustomRuntimeException exception,
                                                                     WebRequest request){
        ErrorMessage errorMessage = buildErrorMessage(exception.getMessage(), request, exception.getDetail());
        return new ResponseEntity<>(errorMessage, exception.getHttpStatus());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception, WebRequest request){
        log.error("RuntimeException occurs: ",  exception);
        ErrorMessage errorMessage = buildErrorMessage(exception.getMessage(), request,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                              WebRequest request) {
        log.error("DataIntegrityViolationException occurs: ", ex);
        String description = "Data Integrity violation";
        ConstraintViolationException exception = (ConstraintViolationException) ex.getCause();
        String message = HttpStatus.CONFLICT.getReasonPhrase();
        if(exception != null && exception.getConstraintName().contains(BOOK_NAME_UNIQUE_CONSTRAINT)){
            message = BOOK_NAME_UNIQUE_CONSTRAINT_MSG;
            description = "Provided Book name already save in DB, please add unique book name";
        } else if(exception != null && exception.getConstraintName().contains(BOOK_ISBN_UNIQUE_CONSTRAINT)){
            message = BOOK_ISBN_UNIQUE_CONSTRAINT_MSG;
            description = "Provided Book ISBN already assign to an other book, please add unique ISBN";
        }
        ErrorMessage errorMessage = buildErrorMessage(message, request, description);
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidationException(MethodArgumentNotValidException ex,
                                                                        WebRequest request) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            ErrorMessage errorMessage = buildErrorMessage(fieldName, request, error.getDefaultMessage());
            errorMessages.add(errorMessage);
        });
        return ResponseEntity.badRequest().body(errorMessages);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                              WebRequest request) {

        ErrorMessage errorMessage = buildErrorMessage("Invalid request", request,
                ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(EntityNotFoundException ex, WebRequest request) {

        ErrorMessage errorMessage = buildErrorMessage(ex.getMessage(), request,
                HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private ErrorMessage buildErrorMessage(String exception, WebRequest request, String detail){
        String path = request.getDescription(false).split("=")[1];
        return ErrorMessage.of().localDateTime(LocalDateTime.now())
                .message(exception)
                .path(path)
                .detail(detail)
                .build();
    }
}