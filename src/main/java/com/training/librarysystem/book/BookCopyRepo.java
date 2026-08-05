package com.training.librarysystem.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepo extends JpaRepository<BookCopy, Long> {
    long countByBookId(Long bookId);

    Optional<BookCopy> findFirstByBookIdAndBookStatusOrderByIdAsc(
            long bookId,
            BookStatus bookStatus
    );
}
