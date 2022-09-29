package com.az.bookstore.service.impl;

import com.az.bookstore.dto.*;
import com.az.bookstore.entity.Book;
import com.az.bookstore.exception.CustomRuntimeException;
import com.az.bookstore.repository.BookRepository;
import com.az.bookstore.service.BookService;
import com.az.bookstore.util.PromotionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public AddBookResponse addBook(AddBookRequest request) {
        log.info("addBook method called with request {}", request);
        Book book = new Book();
        BeanUtils.copyProperties(request.getBookDTO(), book);
        bookRepository.save(book);
        AddBookResponse response = new AddBookResponse();
        response.setBookId(book.getBookId());
        log.info("addBook method response {}", response);
        return response;
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest request) {
        log.info("updateBook method called with request {}", request);
        Book book = fetchBookByBookId(request.getBookId());
        BeanUtils.copyProperties(request.getBookDTO(), book);
        bookRepository.save(book);
        UpdateBookResponse response = new UpdateBookResponse();
        response.setResponse("Success");
        log.info("updateBook method response {}", response);
        return response;
    }

    @Override
    public Book searchBookById(Long bookId) {
        log.info("searchBookById method called with bookId {}", bookId);
        return fetchBookByBookId(bookId);
    }

    @Override
    public boolean deleteBookById(Long bookId) {
        log.info("deleteBookById method called with bookId {}", bookId);
        Book book = fetchBookByBookId(bookId);
        bookRepository.delete(book);
        return true;
    }

    @Override
    public CheckOutResponse checkOutBooks(CheckOutRequest request) {
        log.info("checkOutBooks method called with request {}", request);
        List<Book> bookList = fetchBooksByBookIds(request);
        CheckOutResponse response = new CheckOutResponse();
        buildCheckOutResponse(request, bookList, response);
        log.info("checkOutBooks method response {}", response);
        return response;
    }

    private void buildCheckOutResponse(CheckOutRequest request, List<Book> bookList, CheckOutResponse response) {
        BigDecimal discountedPrice = bookList.stream()
                .map(book -> buildDiscountedPrice(book, request.getPromotionCode()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal actualPrice = bookList.stream().map(Book ::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        response.setDiscountedPrice(discountedPrice);
        response.setActualPrice(actualPrice);
    }

    private BigDecimal buildDiscountedPrice(Book book, String promotionCode) {

        Promotion promotion = null;
        if(StringUtils.hasText(promotionCode)){
            promotion = PromotionUtil.fetchPromotionByPromotionCode(promotionCode);
        }
        return applyPromotion(book, promotion);
    }

    private Book fetchBookByBookId(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        return bookOptional.orElseThrow(() -> new CustomRuntimeException("Invalid bookId",
                "Provided bookId is not exist", HttpStatus.BAD_REQUEST));
    }

    private BigDecimal applyPromotion(Book book, Promotion promotion) {
        if(promotion != null && promotion.getType().equals(book.getType())){
           BigDecimal discountedPrice =  BigDecimal.valueOf(promotion.getDiscount())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).multiply(book.getPrice());
           return book.getPrice().subtract(discountedPrice);
        } else
            return book.getPrice();
    }

    private List<Book> fetchBooksByBookIds(CheckOutRequest request) {
        List<Book> bookList = bookRepository.findAllById(request.getBookIds());
        if(CollectionUtils.isEmpty(bookList))
            throw new CustomRuntimeException("Invalid bookIds", "Provided BookIds are invalid", HttpStatus.BAD_REQUEST);
        return bookList;
    }
}