package com.gladysz.library.dto;


public record BookResponseDto(Long id, String title, String author,
                              int yearOfRelease) {
}
