package com.gladysz.library.mapper;

import com.gladysz.library.domain.Book;
import com.gladysz.library.dto.BookCreateDto;
import com.gladysz.library.dto.BookResponseDto;
import com.gladysz.library.dto.BookUpdateDto;
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
