package com.az.bookstore.dto;

import lombok.RequiredArgsConstructor;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@RequiredArgsConstructor
public enum BookType {

    CRIME("Crime"),
    FICTION("Fiction"),
    POETRY("Poetry"),
    COMIC("Comic");

    private final String type;

    @Override
    public java.lang.String toString() {
        return this.type;
    }
}
