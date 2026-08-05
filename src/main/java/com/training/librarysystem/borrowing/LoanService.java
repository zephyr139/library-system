package com.training.librarysystem.borrowing;

import com.training.librarysystem.book.BookCopy;
import com.training.librarysystem.book.BookCopyRepo;
import com.training.librarysystem.book.BookStatus;
import com.training.librarysystem.user.UserRepo;
import com.training.librarysystem.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {
    private static final int DEFAULT_BORROW_PERIOD_DAYS = 14;

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookCopyRepo bookCopyRepo;

    public Loan borrowBook(long bookId) {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        Users user = userRepo.findByEmail(username);

        Loan loan = new Loan();

//        Finds the first match in the DB
        BookCopy bookCopy = bookCopyRepo.findFirstByBookIdAndBookStatusOrderByIdAsc(bookId,BookStatus.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("Book for such Book copy not found"));

        bookCopy.setBookStatus(BookStatus.BORROWED);

        bookCopyRepo.save(bookCopy);

        loan.setUserId(user.getId());
        loan.setBookCopyId(bookCopy.getId());
        loan.setStatus(LoanStatus.ACTIVE);

        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(DEFAULT_BORROW_PERIOD_DAYS);
        loan.setBorrowDate(borrowDate);
        loan.setDueDate(dueDate);

        return loanRepo.save(loan);
    }

//    reserve book logic
}
