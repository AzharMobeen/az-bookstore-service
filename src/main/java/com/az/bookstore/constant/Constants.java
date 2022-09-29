package com.az.bookstore.constant;

import lombok.experimental.UtilityClass;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@UtilityClass
public class Constants {

    public static final String API_TITLE = "Az Book Store";
    public static final String API_VERSION = "1.0";
    public static final String API_DESCRIPTION = "It's a Simple Online Book Store micro service";
    public static final String BOOK_URI = "/books";

    public static final String BOOK_NAME_UNIQUE_CONSTRAINT = "BOOK_NAME_UNIQUE_CONSTRAINT";
    public static final String BOOK_ISBN_UNIQUE_CONSTRAINT = "BOOK_ISBN_UNIQUE_CONSTRAINT";
    public static final String BOOK_NAME_UNIQUE_CONSTRAINT_MSG = "Book name already exist";
    public static final String BOOK_ISBN_UNIQUE_CONSTRAINT_MSG = "Book ISBN already exist";

    public static final String API_ACCESS_KEY = "API_ACCESS_KEY";
}