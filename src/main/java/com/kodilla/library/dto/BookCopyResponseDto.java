package com.kodilla.library.dto;

import com.kodilla.library.domain.Status;

public record BookCopyResponseDto(Long id, Long bookId, String bookTitle, Status status) {
}
