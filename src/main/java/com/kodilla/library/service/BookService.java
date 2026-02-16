package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public Book saveBook(final Book book) {

        return bookRepository.save(book);
    }


    @Transactional(readOnly = true)
    public Book getBookById(final Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


    public Book updateBook(final Long id, final Book newBook) {

        Book fetchedBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        fetchedBook.setTitle(newBook.getTitle());
        fetchedBook.setAuthor(newBook.getAuthor());
        fetchedBook.setYearOfRelease(newBook.getYearOfRelease());

        fetchedBook.getBookCopies().clear();
        for (BookCopy newBookCopy : newBook.getBookCopies()) {
            newBookCopy.setBook(fetchedBook);
            fetchedBook.addBookCopy(newBookCopy);
        }
        return fetchedBook;
    }
}
