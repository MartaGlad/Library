package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderUpdateDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.repository.ReaderRepository;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

    @InjectMocks
    private ReaderService readerService;

    @Mock
    private ReaderRepository readerRepository;
    @Mock
    private ReaderMapper readerMapper;

    private Reader reader;

    @BeforeEach
    void setUp() {

        reader = new Reader (
                1L,
                "Josephine",
                "Smith",
                null,
                new ArrayList<>()
        );
    }


    @Test
    void testSaveReader() {
        //Given
        when(readerRepository.save(reader)).thenReturn(reader);
        //When
        Reader savedReader = readerService.saveReader(reader);
        //Then
        assertNotNull(savedReader);
        assertEquals(reader.getSurname(), savedReader.getSurname());
        assertEquals(LocalDate.now(), savedReader.getDateOfAccountCreation());
        verify(readerRepository, times(1)).save(reader);
    }


    @Test
    void testGetReaderByIdSuccess() {
        //Given
        when(readerRepository.findById(1L)).thenReturn(Optional.of(reader));
        //When
        Reader foundReader = readerService.getReaderById(1L);
        //Then
        assertEquals(reader.getSurname(), foundReader.getSurname());
    }


    @Test
    void testGetReaderByIdShouldThrowExceptionWhenReaderNotFound() {
        //Given
        when(readerRepository.findById(1L)).thenReturn(Optional.empty());
        //When & Then
        assertThrows(ReaderNotFoundException.class, () ->  readerService.getReaderById(1L));
    }


    @Test
    void testUpdateReader() {
        //Given
        ReaderUpdateDto readerUpdateDto = new ReaderUpdateDto (
                "Joe", "Potty"
        );
        when(readerRepository.findById(1L)).thenReturn(Optional.of(reader));
        //When
        Reader updatedReader = readerService.updateReader(1L, readerUpdateDto);
        //Then
        verify(readerRepository, times(1)).findById(1L);
        verify(readerMapper, times(1)).mapToReader(reader, readerUpdateDto);
    }
}