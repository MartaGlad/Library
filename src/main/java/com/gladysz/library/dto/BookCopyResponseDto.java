package com.gladysz.library.dto;

import com.gladysz.library.domain.Status;

public record BookCopyResponseDto(Long id, Long bookId, String bookTitle, Status status) {
}
