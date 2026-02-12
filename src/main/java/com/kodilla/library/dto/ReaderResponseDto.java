package com.kodilla.library.dto;


import java.time.LocalDateTime;
import java.util.List;

public record ReaderResponseDto(Long id, String name, String surname,
                              LocalDateTime dateOfAccountCreation, List<RentDto> rents) {
}