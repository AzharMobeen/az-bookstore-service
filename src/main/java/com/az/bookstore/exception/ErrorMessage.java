package com.az.bookstore.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Getter
@Builder(builderMethodName = "of")
public class ErrorMessage {
    private LocalDateTime localDateTime;
    private String message;
    private String detail;
    private String path;
}
