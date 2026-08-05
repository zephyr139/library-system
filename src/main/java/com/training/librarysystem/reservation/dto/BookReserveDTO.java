package com.training.librarysystem.reservation.dto;

import java.time.LocalDate;

public record BookReserveDTO(
        long bookId,
        LocalDate reserveDate,
        LocalDate dueDate
) {
}
