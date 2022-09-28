package com.az.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Data
@NoArgsConstructor
public class UpdateBookRequest {

    @NotNull(message = "Please provide bookId")
    private Long bookId;

    @Valid
    private BookDTO bookDTO;

}
