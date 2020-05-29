package com.baeldung.hexagonal.architecture.adapter.input;

import com.baeldung.hexagonal.architecture.domain.Book;
import com.baeldung.hexagonal.architecture.input.BookService;

import java.util.UUID;

// adapter
public class WebApi{

    BookService bookService;

    public WebApi(BookService bookService) {
        this.bookService = bookService;
    }

    public void createBook(String id, String title, String author) {
        Book book = new Book(UUID.randomUUID(), id, title, author);
        bookService.createBook(book);
    }
}
