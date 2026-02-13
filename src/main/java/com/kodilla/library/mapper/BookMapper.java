package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookCreateDto;
import com.kodilla.library.dto.BookResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class BookMapper {

    public Book mapToBook(final BookCreateDto bookCreateDto) {
        return new Book (
                null,
                bookCreateDto.title(),
                bookCreateDto.author(),
                bookCreateDto.yearOfRelease(),
                new ArrayList<>());
    }


    public BookResponseDto mapToBookResponseDto(final Book book) {
       return new BookResponseDto (
               book.getId(),
               book.getTitle(),
               book.getAuthor(),
               book.getYearOfRelease());
    }
}
