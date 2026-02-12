package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderCreateDto;
import com.kodilla.library.dto.ReaderResponseDto;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ReaderMapper {

    public ReaderCreateDto mapToReaderCreateDto(Reader reader) {
        return new ReaderCreateDto(reader.getId(), reader.getName(),
                reader.getSurname(), reader.getDateOfAccountCreation(), List.of());
    }

    public Reader mapToReader(ReaderCreateDto readerCreateDto) {
        return new Reader(null, readerCreateDto.name(), readerCreateDto.surname(),
                readerCreateDto.dateOfAccountCreation(), List.of());
    }

    public ReaderResponseDto mapToReaderResponseDto(Reader reader) {
        return new ReaderResponseDto(reader.getId(), reader.getName(),
                reader.getSurname(), reader.getDateOfAccountCreation(), List.of());
    }

    public Reader mapToReader(ReaderResponseDto readerResponseDto) {
        return new Reader(readerResponseDto.id(), readerResponseDto.name(),
                readerResponseDto.surname(), readerResponseDto.dateOfAccountCreation(),List.of());
    }

}
