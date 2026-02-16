package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookCreateDto;
import com.kodilla.library.dto.BookResponseDto;
import com.kodilla.library.dto.BookUpdateDto;
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


    public void mapToBook(final Book book, final BookUpdateDto bookUpdateDto) {

        if (bookUpdateDto.title() != null) {
            book.setTitle(bookUpdateDto.title());
        }

        if (bookUpdateDto.author() != null) {
            book.setAuthor(bookUpdateDto.author());
        }

        if (bookUpdateDto.yearOfRelease() != null) {
            book.setYearOfRelease(bookUpdateDto.yearOfRelease());
        }

    }


    public BookResponseDto mapToBookResponseDto(final Book book) {

       return new BookResponseDto (
               book.getId(),
               book.getTitle(),
               book.getAuthor(),
               book.getYearOfRelease());
    }
}
