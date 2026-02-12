package com.kodilla.library.dto;


import java.util.List;

public record BookResponseDto(Long id, String title, String author,
                              int yearOfRelease, List<BookCopyResponseDto> bookCopiesDto) {
}
