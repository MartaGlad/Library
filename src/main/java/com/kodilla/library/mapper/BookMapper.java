package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookCopyResponseDto;
import com.kodilla.library.dto.BookCreateDto;
import com.kodilla.library.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class BookMapper {

    private final BookCopyMapper bookCopyMapper;

    public Book mapToBook(BookCreateDto bookCreateDto) {
        return new Book (null, bookCreateDto.title(), bookCreateDto.author(),
                bookCreateDto.yearOfRelease(), List.of());
    }

    public BookCreateDto mapToBookCreateDto(Book book) {
        return new BookCreateDto(book.getTitle(), book.getAuthor(), book.getYearOfRelease());
    }

    public Book mapToBook(BookResponseDto bookResponseDto) {

        List<BookCopyResponseDto> bookCopyResponseDtoList = bookResponseDto.bookCopiesDto();

        return new Book(bookResponseDto.id(), bookResponseDto.title(),
                bookResponseDto.author(), bookResponseDto.yearOfRelease(), List.of());
    }

    public BookResponseDto mapToBookResponseDto(Book book) {
       return new BookResponseDto(book.getId(), book.getTitle(),
               book.getAuthor(), book.getYearOfRelease(), List.of());
    }

}
