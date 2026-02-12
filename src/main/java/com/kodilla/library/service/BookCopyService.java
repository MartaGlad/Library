package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopy save(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    @Transactional(readOnly = true)
    public BookCopy getBookCopyById(final Long id) {
        return bookCopyRepository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public long getBookCopyCountByBookId(final Long bookId) {
        return bookCopyRepository.countByBookId(bookId);
    }

    public long getBookCopyAvailableCountByBookId(final Long bookId) {
        return bookCopyRepository.countByBookIdAndStatus(bookId, Status.AVAILABLE);
    }

    public void deleteBookCopyById(final Long id) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(BookCopyNotFoundException::new);
        bookCopyRepository.delete(bookCopy);
    }
}
