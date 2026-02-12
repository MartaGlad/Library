package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.dto.BookCopyCreateDto;
import com.kodilla.library.dto.BookCopyResponseDto;
import com.kodilla.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookCopyMapper {

    private final BookService bookService;

    public BookCopyResponseDto mapToBookCopyResponseDto(BookCopy bookCopy) {

        return new BookCopyResponseDto(bookCopy.getId(), bookCopy.getBook().getId(),
                bookCopy.getBook().getTitle(), bookCopy.getStatus(), List.of());
    }


    public BookCopy mapToBookCopy(BookCopyResponseDto bookCopyResponseDto) {

        Book book = bookService.getBookById(bookCopyResponseDto.bookId());

        return new BookCopy(bookCopyResponseDto.id(), bookCopyResponseDto.status(),
                book, List.of());

    }


    public BookCopyCreateDto mapToBookCopyCreateDto(BookCopy bookCopy) {
        return new BookCopyCreateDto(bookCopy.getBook().getId(), bookCopy.getStatus());
    }


    public BookCopy mapToBookCopy(BookCopyCreateDto bookCopyCreateDto) {

        Book book = bookService.getBookById(bookCopyCreateDto.bookId());

        return new BookCopy(null, bookCopyCreateDto.status(), book, List.of());

    }

    public List<BookCopyResponseDto> mapToBookCopyResponseDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyResponseDto)
                .collect(Collectors.toList());

    }

    public List<BookCopy> mapToBookCopy(List<BookCopyResponseDto> bookCopyResponseDtoList) {
        return bookCopyResponseDtoList.stream()
                .map(this::mapToBookCopy)
                .collect(Collectors.toList());

    }

}


