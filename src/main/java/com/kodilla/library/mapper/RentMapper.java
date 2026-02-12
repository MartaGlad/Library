package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.dto.RentDto;
import com.kodilla.library.service.BookCopyService;
import com.kodilla.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class RentMapper {

    private final ReaderService readerService;
    private final BookCopyService bookCopyService;


    public Rent mapToRent(RentDto rentDto) {

        Reader reader = readerService.getReaderById(rentDto.readerId());
        BookCopy bookCopy = bookCopyService.getBookCopyById(rentDto.bookCopyId());

        return new Rent(rentDto.id(), rentDto.dateOfRent(),
                rentDto.dateOfReturn(), reader, bookCopy);
    }

    public RentDto mapToRentDto(Rent rent) {
        return new RentDto(rent.getId(), rent.getDateOfRent(),
                rent.getDateOfReturn(),rent.getReader().getId(),
                rent.getBookCopy().getBook().getId());
    }


    public List<RentDto> mapToRentDtoList(List<Rent> rents) {
        return rents.stream().map(this::mapToRentDto).collect(Collectors.toList());
    }

    public List<Rent> mapToRentList(List<RentDto> rentsDto) {
        return rentsDto.stream().map(this::mapToRent).collect(Collectors.toList());
    }
}
