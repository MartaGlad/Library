package com.kodilla.library.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RentDto(Long id, @NotNull LocalDateTime dateOfRent, LocalDateTime dateOfReturn,
                      @NotNull Long readerId, @NotNull Long bookCopyId) {
}
