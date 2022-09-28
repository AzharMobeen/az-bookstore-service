package com.az.bookstore.controller;

import com.az.bookstore.constant.Constants;
import com.az.bookstore.dto.*;
import com.az.bookstore.entity.Book;
import com.az.bookstore.exception.ErrorMessage;
import com.az.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Azhar Mobeen
 * @since 28/09/2022
 */

@Validated
@RequiredArgsConstructor
@RestController(Constants.BOOK_URI)
public class BookController {

    private final BookService bookService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AddBookResponse.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED (You are not authorized for this endpoint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "This API is to add books into store.")
    @PostMapping
    public AddBookResponse addBook(@RequestBody @Valid AddBookRequest request) {
        return bookService.addBook(request);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UpdateBookResponse.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED (You are not authorized for this endpoint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "CONFLICT (Unique Book name constraint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "This API is to update book details.")
    @PutMapping
    public UpdateBookResponse updateBook(@RequestBody @Valid UpdateBookRequest request) {

        return bookService.updateBook(request);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Book.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED (You are not authorized for this endpoint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "This API is to search book by bookId.")
    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable @NotNull(message = "Please provide bookId") Long bookId) {
        return bookService.searchBookById(bookId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Boolean.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED (You are not authorized for this endpoint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "This API is to delete book by bookId.")
    @DeleteMapping("{bookId}")
    public boolean deleteBookById(@PathVariable @NotNull( message = "Please provide bookId") long bookId) {
        return bookService.deleteBookById(bookId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CheckOutResponse.class)
            )}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED (You are not authorized for this endpoint)",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {@Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorMessage.class)
            )})
    })
    @Operation(summary = "This API is provide total price.")
    @PostMapping("/checkout")
    public CheckOutResponse checkOutBooks(@Valid @RequestBody CheckOutRequest request) {
        return bookService.checkOutBooks(request);
    }
}
