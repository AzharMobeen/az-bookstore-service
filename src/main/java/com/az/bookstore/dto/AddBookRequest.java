package com.az.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Data
@NoArgsConstructor
public class AddBookRequest {

    @Valid
    private BookDTO bookDTO;
}
