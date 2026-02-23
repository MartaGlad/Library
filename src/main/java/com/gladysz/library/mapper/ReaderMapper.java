package com.gladysz.library.mapper;

import com.gladysz.library.domain.Reader;
import com.gladysz.library.dto.ReaderCreateDto;
import com.gladysz.library.dto.ReaderResponseDto;
import com.gladysz.library.dto.ReaderUpdateDto;
import com.gladysz.library.dto.RentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReaderMapper {

    private final RentMapper rentMapper;

    public Reader mapToReader(final ReaderCreateDto readerCreateDto) {

        return new Reader (
                null,
                readerCreateDto.name(),
                readerCreateDto.surname(),
                null,
                new ArrayList<>());
    }


    public void mapToReader(final Reader reader, final ReaderUpdateDto readerUpdateDto) {

        if (readerUpdateDto.name() != null) {
            reader.setName(readerUpdateDto.name());
        }

        if (readerUpdateDto.surname() != null) {
            reader.setSurname(readerUpdateDto.surname());
        }
    }


    public ReaderResponseDto mapToReaderResponseDto(final Reader reader) {

        List<RentResponseDto> rentResponseDtoList = rentMapper.mapToRentResponseDtoList(reader.getRents());

        return new ReaderResponseDto (
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getDateOfAccountCreation(),
                rentResponseDtoList);
    }
}
