package com.kodilla.library.dto;

import jakarta.validation.constraints.NotNull;

public record BookCopyCreateDto(@NotNull Long bookId) {
}
