package com.training.librarysystem.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Getter
@Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String isbn;

    private String title;

    private String author;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BookCopy> copies = new ArrayList<>();

    @Formula("(SELECT COUNT(*) FROM book_copies bc WHERE bc.book_id = id)")
    @Column(name = "copies_count")
    private int copies_count;

}
