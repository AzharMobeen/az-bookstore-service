package com.az.bookstore.service.impl;

import com.az.bookstore.dto.AddBookRequest;
import com.az.bookstore.dto.BookType;
import com.az.bookstore.dto.CheckOutRequest;
import com.az.bookstore.dto.UpdateBookRequest;
import com.az.bookstore.exception.CustomRuntimeException;
import com.az.bookstore.repository.BookRepository;
import com.az.bookstore.service.PromotionIntegrationService;
import com.az.bookstore.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */
@DisplayName("BookServiceImpl Test Cases")
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PromotionIntegrationService promotionIntegrationService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook() {
        AddBookRequest request = TestUtil.buildAddBookRequest();
        Mockito.when(bookRepository.save(any())).thenReturn(any());
        assertDoesNotThrow(() -> bookService.addBook( request, "test"));
    }

    @Test
    void updateBook_exception() {
        UpdateBookRequest request = TestUtil.buildUpdateBookRequest();
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(bookRepository.save(any())).thenReturn(any());
        assertThrows(CustomRuntimeException.class, () -> bookService.updateBook(request, "test"),
                "Invalid bookId");
    }

    @Test
    void updateBook_success() {
        UpdateBookRequest request = TestUtil.buildUpdateBookRequest();
        Mockito.when(bookRepository.findById(any())).thenReturn(TestUtil.buildBookOptional());
        Mockito.when(bookRepository.save(any())).thenReturn(any());
        assertDoesNotThrow(() -> bookService.updateBook(request, "test"));
    }

    @Test
    void searchBookById() {
        Mockito.when(bookRepository.findById(any())).thenReturn(TestUtil.buildBookOptional());
        assertDoesNotThrow(() -> bookService.searchBookById( 1l, "test"));
    }

    @Test
    void deleteBookById() {
        Mockito.when(bookRepository.findById(any())).thenReturn(TestUtil.buildBookOptional());
        Mockito.doNothing().when(bookRepository).delete(any());
        assertDoesNotThrow(() -> bookService.deleteBookById(1l, "test"));
    }

    @Test
    void checkOutBooks_exception() {
        Mockito.when(bookRepository.findAllById(any())).thenReturn(new ArrayList<>());
        assertThrows(CustomRuntimeException.class, () -> bookService.checkOutBooks(new CheckOutRequest(), "test")
                , "Invalid bookIds");
    }

    @DisplayName("checkOutBooks_success Without promotion")
    @Test
    void checkOutBooks_success() {
        Mockito.when(bookRepository.findAllById(any())).thenReturn(TestUtil.buildBookList());
        assertDoesNotThrow(() -> bookService.checkOutBooks(new CheckOutRequest(), "test"));
    }

    @DisplayName("checkOutBooks_success With promotion")
    @Test
    void checkOutBooks_success2() {
        String promotionCode = "ABCD1234";
        CheckOutRequest request = TestUtil.buildCheckOutRequest(promotionCode);
        Mockito.when(bookRepository.findAllById(any())).thenReturn(TestUtil.buildBookList());
        Mockito.when(promotionIntegrationService.fetchPromotionByPromotionCode(promotionCode))
                .thenReturn(TestUtil.buildPromotion(promotionCode, BookType.CRIME));
        assertDoesNotThrow(() -> bookService.checkOutBooks(request, "test"));
    }

    @DisplayName("checkOutBooks_success With promotion but not applied")
    @Test
    void checkOutBooks_success3() {
        String promotionCode = "ABCD1234";
        CheckOutRequest request = TestUtil.buildCheckOutRequest(promotionCode);
        Mockito.when(bookRepository.findAllById(any())).thenReturn(TestUtil.buildBookList());
        Mockito.when(promotionIntegrationService.fetchPromotionByPromotionCode(promotionCode))
                .thenReturn(TestUtil.buildPromotion(promotionCode, BookType.POETRY));
        assertDoesNotThrow(() -> bookService.checkOutBooks(request, "test"));
    }
}