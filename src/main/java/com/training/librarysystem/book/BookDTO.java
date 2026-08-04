package com.training.librarysystem.book;

public record BookDTO(
    String isbn,
    String title,
    String author,
    String publisher,
    BookCategory category
) {}
