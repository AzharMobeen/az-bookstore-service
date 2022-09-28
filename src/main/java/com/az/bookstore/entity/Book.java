package com.az.bookstore.entity;

import com.az.bookstore.constant.Constants;
import com.az.bookstore.dto.BookType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */


@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = Constants.BOOK_NAME_UNIQUE_CONSTRAINT, columnNames = {"name"})})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;


    private String name;
    private String description;
    private BigDecimal price;
    private String author;
    @Enumerated
    private BookType type;
    private String isbn;
}