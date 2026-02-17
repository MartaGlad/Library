package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookUpdateDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    private Book book;

    @BeforeEach
    void setUp() {

        book = new Book (
                1L,
                "The Lion King",
                "Justine Korman",
                1994,
                new ArrayList<>()
        );
    }


    @Test
    void testSaveBook() {
        //Given
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book savedBook = bookService.saveBook(book);
        //Then
        assertNotNull(savedBook);
        assertEquals(book, savedBook);
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        verify(bookRepository, times(1)).save(book);
    }


    @Test
    void testGetBookByIdSuccess() {
        //Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        //When
        Book fetchedBook = bookService.getBookById(1L);
        //Then
        assertNotNull(fetchedBook);
        assertEquals(book, fetchedBook);
        assertEquals(book.getAuthor(), fetchedBook.getAuthor());
    }


    @Test
    void testGetBookByIdNotFound() {
        //Given
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
    }


    @Test
    void testUpdateBook() {
        //Given
        BookUpdateDto bookUpdateDto = new BookUpdateDto (
                "About the Dog That Traveled by Train",
                "Roman Pisarski",
                1969);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        //When
        Book bookUpdated = bookService.updateBook(1L, bookUpdateDto);
        //Then
        verify(bookRepository, times(1)).findById(1L);
        verify(bookMapper, times(1)).mapToBook(book, bookUpdateDto);
    }
}