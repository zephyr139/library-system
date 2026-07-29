package com.training.librarysystem.borrowing;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Loan {

    private long id;

    private long userId;

    private long bookCopyId;

    private Date borrowDate;

    private Date dueDate;

    private Date returnDate;

    private LoanStatus status;
}
