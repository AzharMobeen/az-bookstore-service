package com.az.bookstore.util;

import com.az.bookstore.dto.*;
import com.az.bookstore.entity.Book;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */
public class TestUtil {


    public static Promotion buildPromotion(String promotionCode, BookType type) {
        Promotion promotion = new Promotion();
        promotion.setPromotionCode(promotionCode);
        promotion.setType(type);
        promotion.setDiscount(10);
        return promotion;
    }

    public static AddBookRequest buildAddBookRequest() {
        AddBookRequest request = new AddBookRequest();
        request.setBookDTO(buildBookDTO());
        return request;
    }

    private static BookDTO buildBookDTO() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Test");
        bookDTO.setDescription("testing");
        bookDTO.setIsbn("1234567");
        bookDTO.setAuthor("Author");
        bookDTO.setType(BookType.COMIC);
        return bookDTO;
    }

    public static UpdateBookRequest buildUpdateBookRequest() {
        UpdateBookRequest request = new UpdateBookRequest();
        request.setBookDTO(buildBookDTO());
        request.setBookId(1L);
        return request;
    }

    public static Optional<Book> buildBookOptional() {
        Book book = buildBook();
        return Optional.of(book);
    }

    private static Book buildBook() {
        Book book = new Book();
        book.setBookId(1L);
        book.setPrice(BigDecimal.valueOf(100));
        book.setType(BookType.CRIME);
        return book;
    }

    public static List<Book> buildBookList() {
        return Collections.singletonList(buildBook());
    }

    public static CheckOutRequest buildCheckOutRequest(String promotionCode) {
        CheckOutRequest request = new CheckOutRequest();
        if(StringUtils.hasText(promotionCode))
            request.setPromotionCode(promotionCode);
        return request;
    }
}
