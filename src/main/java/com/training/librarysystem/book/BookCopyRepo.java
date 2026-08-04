package com.training.librarysystem.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepo extends JpaRepository<BookCopy, Long> {
    long countByBookId(Long bookId);
}
