package com.gladysz.library.service;

import com.gladysz.library.domain.Book;
import com.gladysz.library.dto.BookUpdateDto;
import com.gladysz.library.exception.BookNotFoundException;
import com.gladysz.library.mapper.BookMapper;
import com.gladysz.library.repository.BookRepository;
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
