package com.kodilla.library.dto;

import java.time.LocalDateTime;

public record RentResponseDto(Long id, LocalDateTime dateOfRent, LocalDateTime dateOfReturn, Long readerId,
                              Long bookCopyId, String bookCopyTitle) {
}
