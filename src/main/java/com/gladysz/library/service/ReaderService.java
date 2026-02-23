package com.gladysz.library.service;

import com.gladysz.library.domain.Reader;
import com.gladysz.library.dto.ReaderUpdateDto;
import com.gladysz.library.exception.ReaderNotFoundException;
import com.gladysz.library.mapper.ReaderMapper;
import com.gladysz.library.repository.ReaderRepository;
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
