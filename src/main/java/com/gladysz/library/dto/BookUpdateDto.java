package com.gladysz.library.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookUpdateDto(@Size(max = 100) String title, @Size(max = 100) String author,
                            @Positive Integer yearOfRelease) {
}