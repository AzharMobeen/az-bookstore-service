package com.az.bookstore.entity;

import com.az.bookstore.constant.Constants;
import com.az.bookstore.dto.BookType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static com.az.bookstore.constant.Constants.BOOK_ISBN_UNIQUE_CONSTRAINT;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = Constants.BOOK_NAME_UNIQUE_CONSTRAINT, columnNames = {"name"}),
    @UniqueConstraint(name = BOOK_ISBN_UNIQUE_CONSTRAINT, columnNames = {"isbn"})})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return bookId != null && Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}