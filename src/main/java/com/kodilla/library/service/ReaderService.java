package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader saveReader(final Reader reader) {

       return readerRepository.save(reader);
    }


    @Transactional(readOnly = true)
    public Reader getReaderById(final Long id) {

        return readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));
    }


    @Transactional(readOnly = true)
    public List<Reader> getAllReaders() {

        return readerRepository.findAll();
    }


    public Reader updateReader(final Long id, final Reader newReader) {

        Reader fetchedReader = readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        fetchedReader.setName(newReader.getName());
        fetchedReader.setSurname(newReader.getSurname());

        fetchedReader.getRents().clear();
        for(Rent newRent : newReader.getRents()) {
            newRent.setReader(fetchedReader);
            fetchedReader.addRent(newRent);
        }
        return fetchedReader;
    }


    public void deleteReaderById(final Long id) {

        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        readerRepository.delete(reader);
    }
}
