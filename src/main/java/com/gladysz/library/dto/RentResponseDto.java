package com.gladysz.library.dto;

import java.time.LocalDate;

public record RentResponseDto(Long id, LocalDate dateOfRent, LocalDate dateOfReturn, Long readerId,
                              Long bookCopyId, String bookCopyTitle) {
}
