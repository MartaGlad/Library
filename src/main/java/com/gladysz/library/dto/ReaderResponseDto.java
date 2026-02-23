package com.gladysz.library.dto;

import java.time.LocalDate;
import java.util.List;

public record ReaderResponseDto(Long id, String name, String surname,
                                LocalDate dateOfAccountCreation, List<RentResponseDto> rents) {
}