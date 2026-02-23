package com.gladysz.library.dto;

import jakarta.validation.constraints.Size;

public record ReaderUpdateDto(@Size(max = 100) String name,
                              @Size(max = 100) String surname) {
}
