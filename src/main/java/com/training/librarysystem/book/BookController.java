package com.training.librarysystem.book;

import com.training.librarysystem.book.dto.BookCopiesDTO;
import com.training.librarysystem.book.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PostMapping("/copies")
    public List<BookCopy> addBookCopies(@RequestBody BookCopiesDTO bookCopiesDTO) {
        return bookService.addBookCopies(bookCopiesDTO.bookId(), bookCopiesDTO.amount());
    }
}
