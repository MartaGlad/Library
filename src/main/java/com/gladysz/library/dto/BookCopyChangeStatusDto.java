package com.gladysz.library.dto;

import com.gladysz.library.domain.Status;
import jakarta.validation.constraints.NotNull;

public record BookCopyChangeStatusDto(@NotNull Status status) {
}
