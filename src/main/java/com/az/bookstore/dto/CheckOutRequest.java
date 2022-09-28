package com.az.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@Data
@NoArgsConstructor
public class CheckOutRequest {

    @NotNull(message = "Please provide bookIds for checkout")
    Set<Long> bookIds;

    private String promotionCode;
}
