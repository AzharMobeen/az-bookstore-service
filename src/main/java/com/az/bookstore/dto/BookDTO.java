package com.az.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class BookDTO {

    @NotEmpty(message = "Book name is required")
    private String name;
    @NotEmpty(message = "Book description is required")
    private String description;
    @NotNull(message = "Book price is required")
    private BigDecimal price;
    @NotEmpty(message = "Book author is required")
    private String author;
    @NotEmpty(message = "Book type is required")
    private String type;
    @NotEmpty(message = "Book ISBN is required")
    private String isbn;
}