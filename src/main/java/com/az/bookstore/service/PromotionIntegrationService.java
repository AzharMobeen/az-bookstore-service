package com.az.bookstore.service;

import com.az.bookstore.dto.Promotion;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */

public interface PromotionIntegrationService {

    Promotion fetchPromotionByPromotionCode(String promotionCode);
}
