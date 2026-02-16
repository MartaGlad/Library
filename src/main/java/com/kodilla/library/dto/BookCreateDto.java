package com.kodilla.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookCreateDto(@NotBlank @Size(max = 100) String title, @NotBlank @Size(max = 100) String author,
                            @Positive int yearOfRelease) {
}
