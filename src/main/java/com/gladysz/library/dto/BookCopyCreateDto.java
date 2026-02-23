package com.gladysz.library.dto;

import jakarta.validation.constraints.NotNull;

public record BookCopyCreateDto(@NotNull Long bookId) {
}
