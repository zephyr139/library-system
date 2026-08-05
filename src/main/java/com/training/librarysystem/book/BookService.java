package com.training.librarysystem.book;

import com.training.librarysystem.book.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookCopyRepo bookCopyRepo;

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();

        book.setIsbn(bookDTO.isbn());
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setPublisher(bookDTO.publisher());

        book.setCategory(bookDTO.category());

        return bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Transactional
    public List<BookCopy> addBookCopies(Long bookId, int amount) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        long existingCopies = bookCopyRepo.countByBookId(bookId);

        List<BookCopy> copies = new ArrayList<>();

        for (int i = 1; i <= amount; i++) {
            long copyNumber = existingCopies + i;

            BookCopy copy = new BookCopy();
            copy.setBook(book);
            copy.setBookStatus(BookStatus.AVAILABLE);
            copy.setInventoryCode(generateInventoryCode(book.getId(), copyNumber));

            copies.add(copy);
        }

        return bookCopyRepo.saveAll(copies);
    }

    private String generateInventoryCode(long bookId, long copyNumber) {
        return "BOOK-" + bookId + "-" + String.format("%04d", copyNumber);
    }

}
