package com.az.bookstore.service;


import com.az.bookstore.dto.*;
import com.az.bookstore.entity.Book;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;



/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
public interface BookService {

    AddBookResponse addBook(@Valid AddBookRequest request, String apiKey);

    UpdateBookResponse updateBook(UpdateBookRequest request, String apiKey);

    Book searchBookById(@NotEmpty(message = "Please provide bookId") Long bookId, String apiKey);

    boolean deleteBookById(Long bookId, String apiKey);

    CheckOutResponse checkOutBooks(CheckOutRequest request, String apiKey);
}
