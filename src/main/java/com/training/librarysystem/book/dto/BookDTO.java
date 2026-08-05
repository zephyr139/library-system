package com.training.librarysystem.book.dto;

import com.training.librarysystem.book.BookCategory;

public record BookDTO(
    String isbn,
    String title,
    String author,
    String publisher,
    BookCategory category
) {}
