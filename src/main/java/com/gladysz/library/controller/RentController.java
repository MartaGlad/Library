package com.gladysz.library.controller;

import com.gladysz.library.domain.Rent;
import com.gladysz.library.domain.Status;
import com.gladysz.library.dto.RentResponseDto;
import com.gladysz.library.mapper.RentMapper;
import com.gladysz.library.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;
    private final RentMapper rentMapper;

    @PostMapping("/borrowing/{bookCopyId}/{readerId}")
    public ResponseEntity<RentResponseDto> addRent (@PathVariable Long bookCopyId, @PathVariable Long readerId) {

        Rent rent = rentService.rentByBookCopyIdAndReaderId(bookCopyId, readerId);

        return ResponseEntity
                .created(URI.create("/rents/borrowing/" + rent.getId()))
                .body(rentMapper.mapToRentResponseDto(rent));
    }


    @PostMapping("/return/{rentId}")
    public ResponseEntity<RentResponseDto> returnBookCopy (@PathVariable Long rentId) {

        Rent rent = rentService.returnBookCopyByRentId(rentId);

        return ResponseEntity.ok(rentMapper.mapToRentResponseDto(rent));
    }


    @PostMapping("/return/{rentId}/damaged")
    public ResponseEntity<RentResponseDto> returnDamagedBookCopy (@PathVariable Long rentId) {

        Rent rent = rentService.returnBookCopyAndProcessPaymentForDamagesByRentId(rentId, Status.DAMAGED);

        return ResponseEntity.ok(rentMapper.mapToRentResponseDto(rent));
    }


    @PostMapping("/return/{rentId}/lost")
    public ResponseEntity<RentResponseDto> returnLostBookCopy (@PathVariable Long rentId) {

        Rent rent = rentService.returnBookCopyAndProcessPaymentForDamagesByRentId(rentId, Status.LOST);

        return ResponseEntity.ok(rentMapper.mapToRentResponseDto(rent));
    }
}
