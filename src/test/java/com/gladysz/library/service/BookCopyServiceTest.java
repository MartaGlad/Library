package com.gladysz.library.service;

import com.gladysz.library.domain.Book;
import com.gladysz.library.domain.BookCopy;
import com.gladysz.library.domain.Status;
import com.gladysz.library.dto.BookCopyCreateDto;
import com.gladysz.library.exception.BookCopyNotFoundException;
import com.gladysz.library.repository.BookCopyRepository;
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
class BookCopyServiceTest {

    @InjectMocks
    private BookCopyService bookCopyService;

    @Mock
    private BookCopyRepository bookCopyRepository;
    @Mock
    private BookService bookService;

    private BookCopy bookCopy;
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

       bookCopy = new BookCopy (
                1L,
                Status.AVAILABLE,
                book,
                new ArrayList<>()
        );
    }


    @Test
    void testAddNewBookCopy() {
        //Given
        BookCopyCreateDto bookCopyCreateDto = new BookCopyCreateDto (1L);
        when(bookService.getBookById(bookCopyCreateDto.bookId())).thenReturn(book);
        when(bookCopyRepository.save(any(BookCopy.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //When
        BookCopy addedBookCopy = bookCopyService.addNewBookCopy(bookCopyCreateDto);
        //Then
        assertNotNull(addedBookCopy);
        assertEquals(Status.AVAILABLE, addedBookCopy.getStatus());
        assertEquals(book, addedBookCopy.getBook());
        verify(bookService, times(1)).getBookById(bookCopyCreateDto.bookId());
        verify(bookCopyRepository, times(1)).save(addedBookCopy);
    }


    @Test
    void testGetBookCopyByIdSuccess() {
        //Given
        when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(bookCopy));
        //When
        BookCopy foundBookCopy = bookCopyService.getBookCopyById(1L);
        //Then
        assertEquals(bookCopy, foundBookCopy);
        assertEquals(bookCopy.getBook(), foundBookCopy.getBook());
        assertEquals(bookCopy.getStatus(), foundBookCopy.getStatus());
        verify(bookCopyRepository, times(1)).findById(1L);
    }


    @Test
    void testGetBookCopyByIdShouldThrowExceptionWhenBookCopyNotFound() {
        //Given
        when(bookCopyRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(BookCopyNotFoundException.class, () ->  bookCopyService.getBookCopyById(1L));
    }


    @Test
    void testChangeBookCopyStatusByIdSuccess() {
        //Given
        when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(bookCopy));
        Status statusBeforeChange = bookCopy.getStatus();
        //When
        BookCopy bookCopyChanged = bookCopyService.changeBookCopyStatusById(1L, Status.LOST);
        //Then
        assertNotEquals(statusBeforeChange, bookCopyChanged.getStatus());
        assertEquals(Status.LOST, bookCopyChanged.getStatus());
        verify(bookCopyRepository, times(1)).findById(1L);
    }


    @Test
    void testChangeBookCopyStatusByIdShouldThrowExceptionWhenBookCopyNotFound() {
        //Given
        when(bookCopyRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(BookCopyNotFoundException.class,
                () ->  bookCopyService.changeBookCopyStatusById(1L, Status.LOST));
    }


    @Test
    void testGetBookCopyAvailableCountByBookId() {
        //Given
        when(bookCopyRepository.countByBookIdAndStatus(1L, Status.AVAILABLE)).thenReturn(20L);
        //When
        Long result = bookCopyService.getBookCopyAvailableCountByBookId(1L);
        //Then
        assertEquals(20L, result);
        verify(bookCopyRepository, times(1)).countByBookIdAndStatus(1L, Status.AVAILABLE);
    }
}