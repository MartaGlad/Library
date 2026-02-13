package com.kodilla.library.controller;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.dto.BookCopyChangeStatusRequestDto;
import com.kodilla.library.dto.BookCopyCreateDto;
import com.kodilla.library.dto.BookCopyResponseDto;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.BookCopyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/book-copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final BookCopyMapper bookCopyMapper;
    private final BookCopyService bookCopyService;

    @PostMapping
    public ResponseEntity<BookCopyResponseDto> addBookCopy (@Valid @RequestBody BookCopyCreateDto bookCopyCreateDto) {

        BookCopy bookCopy = bookCopyService.addNewBookCopy(bookCopyCreateDto);

        return ResponseEntity
                .created(URI.create("/book-copies/" + bookCopy.getId()))
                .body(bookCopyMapper.mapToBookCopyResponseDto(bookCopy));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<BookCopyResponseDto> changeStatus (
            @PathVariable Long id, @Valid @RequestBody BookCopyChangeStatusRequestDto bookCopyChangeStatusRequestDto) {

        BookCopy bookCopyUpdated = bookCopyService.changeBookCopyStatusById(id, bookCopyChangeStatusRequestDto.status());

        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyResponseDto(bookCopyUpdated));
    }


    @GetMapping("/{bookId}/available-count")
    public ResponseEntity<Long> getBookCopyAvailableCount (@PathVariable Long bookId) {

        return ResponseEntity.ok(bookCopyService.getBookCopyAvailableCountByBookId(bookId));
    }
}

