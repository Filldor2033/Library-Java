package com.filldor.library.book.controller;

public record CreateBookRequest(
        String title,
        String author,
        String isbn,
        String genre,
        Integer publicationYear
) {
}
