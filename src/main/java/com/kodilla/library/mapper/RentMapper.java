package com.kodilla.library.mapper;


import com.kodilla.library.domain.Rent;
import com.kodilla.library.dto.RentResponseDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class RentMapper {

    public RentResponseDto mapToRentResponseDto(final Rent rent) {

        return new RentResponseDto (
                rent.getId(),
                rent.getDateOfRent(),
                rent.getDateOfReturn(),
                rent.getReader().getId(),
                rent.getBookCopy().getBook().getId(),
                rent.getBookCopy().getBook().getTitle());
    }


    public List<RentResponseDto> mapToRentResponseDtoList(final List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentResponseDto)
                .collect(Collectors.toList());
    }
}
