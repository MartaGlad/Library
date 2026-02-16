package com.kodilla.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public record ReaderRequestDto(@NotBlank @Size(max = 100) String name,
                               @NotBlank @Size(max = 100) String surname,
                               @NotNull LocalDateTime dateOfAccountCreation) {
}
