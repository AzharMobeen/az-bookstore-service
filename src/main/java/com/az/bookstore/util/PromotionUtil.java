package com.az.bookstore.util;

import com.az.bookstore.dto.BookType;
import com.az.bookstore.dto.Promotion;
import com.az.bookstore.exception.CustomRuntimeException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@UtilityClass
public class PromotionUtil {

    private final List<Promotion> promotionList = new ArrayList<>();
    static {
        String promotionCode = "ABCD1234";
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(promotionCode);
        promotion.setType(BookType.CRIME);
        promotion.setDiscount(10);
        promotionList.add(promotion);
        Promotion promotion2 = new Promotion();
        promotion.setPromotionCode("WXYZ");
        promotion.setType(BookType.FICTION);
        promotion.setDiscount(5);
        promotionList.add(promotion2);
    }

    public static Promotion fetchPromotionByPromotionCode(String promotionCode) {
        return promotionList.stream().filter(promotion -> promotion.getPromotionCode().equals(promotionCode))
                .findFirst().orElseThrow(() -> new CustomRuntimeException("Invalid PromotionCode",
                        "Provide PromotionCod is invalid", HttpStatus.BAD_REQUEST));
    }
}