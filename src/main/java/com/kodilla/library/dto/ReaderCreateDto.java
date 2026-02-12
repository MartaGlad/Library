package com.kodilla.library.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.List;

public record ReaderCreateDto(Long id, @NotBlank @Size(max = 100) String name,
                              @NotBlank @Size(max = 100) String surname,
                              @NotNull LocalDateTime dateOfAccountCreation, @NotNull @Valid List<RentDto> rents) {
}
