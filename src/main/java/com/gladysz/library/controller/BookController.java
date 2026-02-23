package com.gladysz.library.controller;

import com.gladysz.library.domain.Book;
import com.gladysz.library.dto.BookCreateDto;
import com.gladysz.library.dto.BookResponseDto;
import com.gladysz.library.dto.BookUpdateDto;
import com.gladysz.library.mapper.BookMapper;
import com.gladysz.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook (@Valid @RequestBody BookCreateDto bookCreateDto) {

        Book book = bookService.saveBook(bookMapper.mapToBook(bookCreateDto));

        return ResponseEntity
                .created(URI.create("/books/" + book.getId()))
                .body(bookMapper.mapToBookResponseDto(book));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook
            (@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {

        Book bookUpdated = bookService.updateBook(id, bookUpdateDto);

        return ResponseEntity.ok().body(bookMapper.mapToBookResponseDto(bookUpdated));

    }
}


