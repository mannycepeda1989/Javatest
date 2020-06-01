package com.baeldung.hexagonal.architecture.domain;

import com.baeldung.hexagonal.architecture.adapter.outgoing.BookWriterStub;
import com.baeldung.hexagonal.architecture.port.outgoing.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceDomainUnitTest {

    private BookWriterStub writer;
    private BookRepository bookRepository;
    private BookServiceDomain bookServiceDomain;

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        writer = new BookWriterStub();
        bookServiceDomain = new BookServiceDomain(bookRepository, writer);
    }

    @Test
    void givenListOfBooks_whenInvoke_thenListBooksWithCapitalizedTitleAndAuthor() {
        Book book1 = new Book("Effective Java 3rd Edition", "Joshua Bloch");
        Book book2 = new Book("Building Microservices: Designing Fine-Grained Systems", "Sam Newman");
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.getAllBooks()).thenReturn(books);

        bookServiceDomain.invoke();

        verify(bookRepository).getAllBooks();
        List<String> lines = writer.getWrittenOutLines();
        assertThat(lines.get(0)).isEqualTo("EFFECTIVE JAVA 3RD EDITION - Joshua Bloch");
        assertThat(lines.get(1)).isEqualTo("BUILDING MICROSERVICES: DESIGNING FINE-GRAINED SYSTEMS - Sam Newman");
    }

    @Test
    void givenNoBooksPresent_whenInvoke_thenShouldReturnEmptyListOfString() {
        when(bookRepository.getAllBooks()).thenReturn(Collections.emptyList());

        bookServiceDomain.invoke();

        verify(bookRepository).getAllBooks();
        List<String> lines = writer.getWrittenOutLines();
        assertThat(lines).isEmpty();
    }
}
