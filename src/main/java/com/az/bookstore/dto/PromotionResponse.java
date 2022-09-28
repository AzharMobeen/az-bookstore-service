package com.az.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Data
@NoArgsConstructor
public class PromotionResponse {

    List<Promotion> promotions;
}
