package com.training.librarysystem.user.librarian;

import com.training.librarysystem.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PostMapping("/addCopies")
    public List<BookCopy> addBookCopies(@RequestBody BookCopiesDTO bookCopiesDTO) {
        return bookService.addBookCopies(bookCopiesDTO.bookId(),bookCopiesDTO.amount());
    }

}
