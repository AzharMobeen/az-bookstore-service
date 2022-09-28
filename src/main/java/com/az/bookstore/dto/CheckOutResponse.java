package com.az.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@Data
@NoArgsConstructor
public class CheckOutResponse {

    private BigDecimal totalPrice;
}
