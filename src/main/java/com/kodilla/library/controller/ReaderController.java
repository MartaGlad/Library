package com.kodilla.library.controller;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderCreateDto;
import com.kodilla.library.dto.ReaderResponseDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderMapper readerMapper;
    private final ReaderService readerService;

    @PostMapping
    public ResponseEntity<ReaderResponseDto> addReader(@Valid @RequestBody ReaderCreateDto readerCreateDto) {

        Reader reader = readerService.saveReader(readerMapper.mapToReader(readerCreateDto));

        return ResponseEntity
                .created(URI.create("/readers/" + reader.getId()))
                .body(readerMapper.mapToReaderResponseDto(reader));
    }
}
