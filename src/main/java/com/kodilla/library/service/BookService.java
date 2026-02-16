package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.dto.BookUpdateDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public Book saveBook(final Book book) {

        return bookRepository.save(book);
    }


    @Transactional(readOnly = true)
    public Book getBookById(final Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


    public Book updateBook(final Long id, final BookUpdateDto bookUpdateDto) {

        Book fetchedBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookMapper.mapToBook(fetchedBook, bookUpdateDto);

        return fetchedBook;
    }
}
