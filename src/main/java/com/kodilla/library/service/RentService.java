package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Status;
import com.kodilla.library.exception.*;
import com.kodilla.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final BookCopyService bookCopyService;
    private final ReaderService readerService;


    public Rent rentByBookCopyIdAndReaderId(final Long bookCopyId, final Long readerId) {

        BookCopy fetchedBookCopy = bookCopyService.getBookCopyById(bookCopyId);

        if (fetchedBookCopy.getStatus() != Status.AVAILABLE)
            throw new BookCopyChangeStatusException();

        Reader fetchedReader = readerService.getReaderById(readerId);

        Rent newRent = new Rent();

        newRent.setDateOfRent(LocalDate.now());

        newRent.setReader(fetchedReader);

        newRent.setBookCopy(fetchedBookCopy);
        fetchedBookCopy.changeStatus(Status.RENTED);

        return rentRepository.save(newRent);
    }


    public Rent returnBookCopyByRentId(final Long id) {

        Rent fetchedRent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));

        if (fetchedRent.getDateOfReturn() != null)
            throw new RentAlreadyCompletedException(id);

        BookCopy fetchedBookCopy = fetchedRent.getBookCopy();
        fetchedBookCopy.changeStatus(Status.AVAILABLE);

        fetchedRent.completeRent();

        return fetchedRent;
    }


    public Rent returnBookCopyAndProcessPaymentForDamagesByRentId(final Long id, final Status newStatus) {

        Rent fetchedRent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));

        if (fetchedRent.getDateOfReturn() != null)
            throw new RentAlreadyCompletedException(id);

        BookCopy fetchedBookCopy = fetchedRent.getBookCopy();
        fetchedBookCopy.changeStatus(newStatus);

        fetchedRent.completeRent();

        return fetchedRent;
    }
}
