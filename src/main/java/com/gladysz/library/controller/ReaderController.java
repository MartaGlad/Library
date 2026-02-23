package com.gladysz.library.controller;

import com.gladysz.library.domain.Reader;
import com.gladysz.library.dto.ReaderCreateDto;
import com.gladysz.library.dto.ReaderResponseDto;
import com.gladysz.library.dto.ReaderUpdateDto;
import com.gladysz.library.mapper.ReaderMapper;
import com.gladysz.library.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderMapper readerMapper;
    private final ReaderService readerService;

    @PostMapping
    public ResponseEntity<ReaderResponseDto> addReader (@Valid @RequestBody ReaderCreateDto readerCreateDto) {

        Reader reader = readerService.saveReader(readerMapper.mapToReader(readerCreateDto));

        return ResponseEntity
                .created(URI.create("/readers/" + reader.getId()))
                .body(readerMapper.mapToReaderResponseDto(reader));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ReaderResponseDto> updateReader (
            @PathVariable Long id, @Valid @RequestBody ReaderUpdateDto readerUpdateDto) {

        Reader readerUpdated = readerService.updateReader(id, readerUpdateDto);

        return ResponseEntity.ok(readerMapper.mapToReaderResponseDto(readerUpdated));
    }
}
