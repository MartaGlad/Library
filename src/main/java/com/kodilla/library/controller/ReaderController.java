package com.kodilla.library.controller;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.ReaderRequestDto;
import com.kodilla.library.dto.ReaderResponseDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.ReaderService;
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
    public ResponseEntity<ReaderResponseDto> addReader (@Valid @RequestBody ReaderRequestDto readerRequestDto) {

        Reader reader = readerService.saveReader(readerMapper.mapToReader(readerRequestDto));

        return ResponseEntity
                .created(URI.create("/readers/" + reader.getId()))
                .body(readerMapper.mapToReaderResponseDto(reader));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReaderResponseDto> updateReader (
            @PathVariable Long id, @Valid @RequestBody ReaderRequestDto readerRequestDto) {

        Reader readerUpdated = readerService.updateReader(id, readerMapper.mapToReader(readerRequestDto));

        return ResponseEntity.ok(readerMapper.mapToReaderResponseDto(readerUpdated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {

        readerService.deleteReaderById(id);

        return ResponseEntity.noContent().build();
    }
}
