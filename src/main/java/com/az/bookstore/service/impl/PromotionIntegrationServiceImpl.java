package com.az.bookstore.service.impl;

import com.az.bookstore.dto.BookType;
import com.az.bookstore.dto.Promotion;
import com.az.bookstore.exception.CustomRuntimeException;
import com.az.bookstore.service.PromotionIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */

@Slf4j
@Service
public class PromotionIntegrationServiceImpl implements PromotionIntegrationService {

    // Ideally we should have Promotion Microservice which will deal all the promotion related business-logic.

    private static final List<Promotion> promotionList = new ArrayList<>();
    static {
        String promotionCode = "ABCD1234";
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(promotionCode);
        promotion.setType(BookType.CRIME);
        promotion.setDiscount(10);
        promotionList.add(promotion);
        Promotion promotion2 = new Promotion();
        promotion2.setPromotionCode("WXYZ");
        promotion2.setType(BookType.FICTION);
        promotion2.setDiscount(5);
        promotionList.add(promotion2);
    }

    @Override
    public Promotion fetchPromotionByPromotionCode(String promotionCode) {
        log.info("fetchPromotionByPromotionCode called for promotionCode {}", promotionCode);
        Promotion response = promotionList.stream()
                .filter(promotion -> promotion.getPromotionCode().equals(promotionCode))
                .findFirst().orElseThrow(() -> new CustomRuntimeException("Invalid PromotionCode",
                        "Provide PromotionCod is invalid", HttpStatus.BAD_REQUEST));
        log.info(" response {}", response);
        return response;
    }
}