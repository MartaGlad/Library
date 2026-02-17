package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.exception.BookCopyChangeStatusException;
import com.kodilla.library.exception.RentAlreadyCompletedException;
import com.kodilla.library.exception.RentNotFoundException;
import com.kodilla.library.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {

    @InjectMocks
    private RentService rentService;

    @Mock
    private RentRepository rentRepository;
    @Mock
    private BookCopyService bookCopyService;
    @Mock
    private ReaderService readerService;

    private Rent rent;
    private Reader reader;
    private BookCopy bookCopy;


    @BeforeEach
    void setUp() {

        reader = new Reader (
                1L,
                "Josephine",
                "Smith",
                null,
                new ArrayList<>()
        );

        bookCopy = new BookCopy (
                1L,
                Status.AVAILABLE,
                new Book(),
                new ArrayList<>()
        );

        rent = new Rent (
                1L,
                null,
                null,
                reader,
                bookCopy
        );
    }


    @Test
    void testRentByBookCopyIdAndReaderIdSuccess() {
        //Given
        when(readerService.getReaderById(1L)).thenReturn(reader);
        when(bookCopyService.getBookCopyById(1L)).thenReturn(bookCopy);
        when(rentRepository.save(any(Rent.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        //When
        Rent newRent = rentService.rentByBookCopyIdAndReaderId(1L, 1L);
        //Then
        assertNotNull(newRent);
        assertEquals(bookCopy, newRent.getBookCopy());
        assertEquals(reader, newRent.getReader());
        assertEquals(Status.RENTED, newRent.getBookCopy().getStatus());
        assertEquals(LocalDate.now(), newRent.getDateOfRent());
        verify(rentRepository, times(1)).save(any(Rent.class));
    }


    @Test
    void testRentByBookCopyIdAndReaderIdShouldThrowExceptionWhenBookCopyIsNotAvailable() {
        //Given
        bookCopy.changeStatus(Status.RENTED);
        when(bookCopyService.getBookCopyById(1L)).thenReturn(bookCopy);
        //When & Then
        assertThrows(BookCopyChangeStatusException.class,
                () -> rentService.rentByBookCopyIdAndReaderId(1L, 1L));
        verify(bookCopyService, times(1)).getBookCopyById(1L);
        verify(readerService, never()).getReaderById(1L);
        verify(rentRepository, never()).save(any(Rent.class));
    }


    @Test
    void testReturnBookCopyByRentIdSuccess() {
        //Given
        bookCopy.changeStatus(Status.RENTED);
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rent));
        //When
        Rent returnedRent = rentService.returnBookCopyByRentId(1L);
        //Then
        assertNotNull(returnedRent);
        assertEquals(bookCopy, returnedRent.getBookCopy());
        assertEquals(Status.AVAILABLE, returnedRent.getBookCopy().getStatus());
        assertEquals(LocalDate.now(), returnedRent.getDateOfReturn());
        verify(rentRepository, times(1)).findById(1L);
    }


    @Test
    void testReturnBookCopyByRentIdShouldThrowExceptionWhenRentNotFound() {
        //Given
        when(rentRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(RentNotFoundException.class, () -> rentService.returnBookCopyByRentId(1L));
        verify(rentRepository, times(1)).findById(1L);
    }


    @Test
    void testReturnBookCopyByRentIdShouldThrowExceptionWhenRentAlreadyCompleted() {
        //Given
        rent.completeRent();
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rent));
        //When & Then
        assertThrows(RentAlreadyCompletedException.class, () -> rentService.returnBookCopyByRentId(1L));
        verify(rentRepository, times(1)).findById(1L);
    }


    @Test
    void testReturnBookCopyAndProcessPaymentForDamagesByRentIdSuccess() {
        //Given
        bookCopy.changeStatus(Status.RENTED);
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rent));
        //When
        Rent returnedRent = rentService.returnBookCopyAndProcessPaymentForDamagesByRentId(1L, Status.DAMAGED);
        //Then
        assertNotNull(returnedRent);
        assertEquals(bookCopy, returnedRent.getBookCopy());
        assertEquals(Status.DAMAGED, returnedRent.getBookCopy().getStatus());
        assertEquals(LocalDate.now(), returnedRent.getDateOfReturn());
        verify(rentRepository, times(1)).findById(1L);
    }


    @Test
    void testReturnBookCopyAndProcessPaymentForDamagesByRentIdShouldThrowExceptionWhenRentNotFound() {
        //Given
        when(rentRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(RentNotFoundException.class,
                () -> rentService.returnBookCopyAndProcessPaymentForDamagesByRentId(1L, Status.DAMAGED));
        verify(rentRepository, times(1)).findById(1L);
    }


    @Test
    void testReturnBookCopyAndProcessPaymentForDamagesByRentIdShouldThrowExceptionWhenRentAlreadyCompleted() {
        //Given
        rent.completeRent();
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rent));
        //When & Then
        assertThrows(RentAlreadyCompletedException.class,
                () -> rentService.returnBookCopyAndProcessPaymentForDamagesByRentId(1L, Status.DAMAGED));
        verify(rentRepository, times(1)).findById(1L);
    }
}
