package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderUpdateDto;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public Reader saveReader(final Reader reader) {
        reader.setDateOfAccountCreation(LocalDate.now());

       return readerRepository.save(reader);
    }


    @Transactional(readOnly = true)
    public Reader getReaderById(final Long id) {

        return readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));
    }


    public Reader updateReader(final Long id, final ReaderUpdateDto readerUpdateDto) {

        Reader fetchedReader = readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        readerMapper.mapToReader(fetchedReader, readerUpdateDto);

        return fetchedReader;
    }
}
