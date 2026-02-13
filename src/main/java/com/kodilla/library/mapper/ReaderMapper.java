package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderCreateDto;
import com.kodilla.library.dto.ReaderResponseDto;
import com.kodilla.library.dto.RentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReaderMapper {

    private final RentMapper rentMapper;

    public Reader mapToReader(final ReaderCreateDto readerCreateDto) {

        return new Reader(
                null,
                readerCreateDto.name(),
                readerCreateDto.surname(),
                readerCreateDto.dateOfAccountCreation(),
                new ArrayList<>());
    }


    public ReaderResponseDto mapToReaderResponseDto(final Reader reader) {

        List<RentResponseDto> rentResponseDtoList = rentMapper.mapToRentResponseDtoList(reader.getRents());

        return new ReaderResponseDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getDateOfAccountCreation(),
                rentResponseDtoList);
    }
}
