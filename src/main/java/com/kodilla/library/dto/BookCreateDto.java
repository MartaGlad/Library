package com.kodilla.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BookCreateDto(@NotBlank String title, @NotBlank String author, @Positive int yearOfRelease) {
}
