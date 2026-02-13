package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.dto.BookCopyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    public BookCopyResponseDto mapToBookCopyResponseDto(final BookCopy bookCopy) {

        return new BookCopyResponseDto(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getBook().getTitle(),
                bookCopy.getStatus());
    }

}


