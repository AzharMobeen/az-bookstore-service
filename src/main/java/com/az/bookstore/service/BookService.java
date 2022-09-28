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

    AddBookResponse addBook(@Valid AddBookRequest request);

    UpdateBookResponse updateBook(UpdateBookRequest request);

    Book searchBookById(@NotEmpty(message = "Please provide bookId") Long bookId);

    boolean deleteBookById(Long bookId);

    CheckOutResponse checkOutBooks(CheckOutRequest request);
}
