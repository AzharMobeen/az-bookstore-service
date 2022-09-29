package com.az.bookstore.dto;


import lombok.Data;


/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Data
public class Promotion {

    private String promotionCode;
    // Discount in percentage
    private Integer discount;
    private BookType type;
}
