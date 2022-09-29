package com.az.bookstore.service.impl;

import com.az.bookstore.dto.BookType;
import com.az.bookstore.dto.Promotion;
import com.az.bookstore.exception.CustomRuntimeException;
import com.az.bookstore.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */
class PromotionIntegrationServiceImplTest {

    @InjectMocks
    private PromotionIntegrationServiceImpl promotionIntegrationService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchPromotionByPromotionCode_success() {
        String promotionCode = "ABCD1234";
        Promotion promotion = TestUtil.buildPromotion(promotionCode, BookType.CRIME);
        assertEquals(promotionIntegrationService.fetchPromotionByPromotionCode(promotionCode), promotion);
    }

    @Test
    void fetchPromotionByPromotionCode_exception() {
        String promotionCode = "ABCD";
        Promotion promotion = TestUtil.buildPromotion(promotionCode, BookType.CRIME);
        assertThrows(CustomRuntimeException.class, () ->
                promotionIntegrationService.fetchPromotionByPromotionCode(promotionCode),"Invalid PromotionCode");
    }
}