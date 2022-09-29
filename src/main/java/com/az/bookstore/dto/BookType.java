package com.az.bookstore.dto;

import lombok.RequiredArgsConstructor;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */
@RequiredArgsConstructor
public enum BookType {

    CRIME("CRIME"),
    FICTION("FICTION"),
    POETRY("POETRY"),
    COMIC("COMIC");
    private final String type;

    @Override
    public java.lang.String toString() {
        return this.type;
    }
}
