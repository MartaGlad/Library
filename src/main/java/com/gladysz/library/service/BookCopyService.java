package com.gladysz.library.service;

import com.gladysz.library.domain.Book;
import com.gladysz.library.domain.BookCopy;
import com.gladysz.library.domain.Status;
import com.gladysz.library.dto.BookCopyCreateDto;
import com.gladysz.library.exception.BookCopyNotFoundException;
import com.gladysz.library.repository.BookCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookService bookService;

    public BookCopy addNewBookCopy(final BookCopyCreateDto bookCopyCreateDto) {

        Book book = bookService.getBookById(bookCopyCreateDto.bookId());

        BookCopy newBookCopy = new BookCopy();
        newBookCopy.setBook(book);

        return bookCopyRepository.save(newBookCopy);
    }


    @Transactional(readOnly = true)
    public BookCopy getBookCopyById(final Long id) {

        return bookCopyRepository.findById(id)
                .orElseThrow(() -> new BookCopyNotFoundException(id));
    }


    public BookCopy changeBookCopyStatusById(final Long id, final Status newStatus) {

        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BookCopyNotFoundException(id));

        bookCopy.changeStatus(newStatus);
        return bookCopy;
    }


    public long getBookCopyAvailableCountByBookId(final Long bookId) {

        return bookCopyRepository.countByBookIdAndStatus(bookId, Status.AVAILABLE);
    }
}
