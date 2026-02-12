package com.kodilla.library.dto;

import com.kodilla.library.domain.Status;
import java.util.List;

public record BookCopyResponseDto(Long id, Long bookId, String bookTitle, Status status,
                                  List<RentDto> rentsDto) {
}
