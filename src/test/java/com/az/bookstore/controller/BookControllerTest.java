package com.az.bookstore.controller;

import com.az.bookstore.dto.AddBookRequest;
import com.az.bookstore.dto.CheckOutRequest;
import com.az.bookstore.dto.UpdateBookRequest;
import com.az.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */

@DisplayName("BookController Test Cases")
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookServiceImpl bookService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook() {
        AddBookRequest request = new AddBookRequest();
        assertDoesNotThrow(() -> bookController.addBook( "", request));
    }

    @Test
    void updateBook() {
        UpdateBookRequest request = new UpdateBookRequest();
        assertDoesNotThrow(() -> bookController.updateBook("", request));
    }

    @Test
    void findBook() {
        Long bookId = 1L;
        assertDoesNotThrow(() -> bookController.findBook("", bookId));
    }

    @Test
    void deleteBookById() {
        long bookId = 1L;
        assertDoesNotThrow(() -> bookController.deleteBookById("", bookId));
    }

    @Test
    void checkOutBooks() {
        CheckOutRequest request = new CheckOutRequest();
        assertDoesNotThrow(() -> bookController.checkOutBooks("", request));
    }
}