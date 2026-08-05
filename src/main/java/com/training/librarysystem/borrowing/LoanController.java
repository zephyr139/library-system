package com.training.librarysystem.borrowing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/borrow/{bookId}")
    public Loan borrowBook(@PathVariable long bookId) {
        return loanService.borrowBook(bookId);
    }
}
