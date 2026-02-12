package com.kodilla.library.dto;

import com.kodilla.library.domain.Status;
import jakarta.validation.constraints.NotNull;

public record BookCopyCreateDto(@NotNull Long bookId, @NotNull Status status) {
}
