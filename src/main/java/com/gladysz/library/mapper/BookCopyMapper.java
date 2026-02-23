package com.gladysz.library.mapper;

import com.gladysz.library.domain.BookCopy;
import com.gladysz.library.dto.BookCopyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

    public BookCopyResponseDto mapToBookCopyResponseDto(final BookCopy bookCopy) {

        return new BookCopyResponseDto(
                bookCopy.getId(),
                bookCopy.getId(),
                bookCopy.getBook().getTitle(),
                bookCopy.getStatus());
    }

}


