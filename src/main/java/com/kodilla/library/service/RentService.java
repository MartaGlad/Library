package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Status;
import com.kodilla.library.exception.BookCopyChangeStatusException;
import com.kodilla.library.exception.RentAlreadyCompletedException;
import com.kodilla.library.exception.RentNotFoundException;
import com.kodilla.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final BookCopyService bookCopyService;
    private final ReaderService readerService;

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }


    @Transactional(readOnly = true)
    public Rent getRentById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
    }


    @Transactional(readOnly = true)
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }


    public void rentByBookCopyIdAndReaderId(final Long bookCopyId, final Long readerId) {
        BookCopy fetchedBookCopy = bookCopyService.getBookCopyById(bookCopyId);

        if (fetchedBookCopy.getStatus() != Status.AVAILABLE)
            throw new BookCopyChangeStatusException();

        Reader fetchedReader = readerService.getReaderById(readerId);

        Rent newRent = new Rent();

        newRent.setDateOfRent(LocalDateTime.now());

        newRent.setReader(fetchedReader);

        newRent.setBookCopy(fetchedBookCopy);
        fetchedBookCopy.markAsRented();

        rentRepository.save(newRent);
    }


    public void returnBookCopyByRentId(final Long id) {
        Rent fetchedRent = rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);

        if(fetchedRent.getDateOfReturn() != null)
            throw new RentAlreadyCompletedException();

        BookCopy fetchedBookCopy = fetchedRent.getBookCopy();
        fetchedBookCopy.markAsAvailable();

        fetchedRent.completeRent();
    }


    public void processPaymentForLostBookCopyByRentId(final Long id) {
        Rent fetchedRent = rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);

        if(fetchedRent.getDateOfReturn() != null)
            throw new RentAlreadyCompletedException();

        BookCopy fetchedBookCopy = fetchedRent.getBookCopy();
        fetchedBookCopy.markAsLost();

        fetchedRent.completeRent();
    }

    public void processPaymentForDamagedBookCopyByRentId(final Long id) {
        Rent fetchedRent = rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);

        if(fetchedRent.getDateOfReturn() != null)
            throw new RentAlreadyCompletedException();

        BookCopy fetchedBookCopy = fetchedRent.getBookCopy();
        fetchedBookCopy.markAsDamaged();

        fetchedRent.completeRent();
    }


    public void deleteRent(final Long id) {
        Rent fetchRent = rentRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
        rentRepository.delete(fetchRent);
    }

}
