package com.training.librarysystem.book;

import lombok.Getter;

public enum BookCategory {
    FICTION_GENERAL("General Fiction", true),
    ACTION_ADVENTURE("Action & Adventure", true),
    FANTASY("Fantasy", true),
    SCIENCE_FICTION("Science Fiction", true),
    MYSTERY_THRILLER("Mystery & Thriller", true),
    ROMANCE("Romance", true),
    HORROR("Horror", true),
    HISTORICAL_FICTION("Historical Fiction", true),
    YOUNG_ADULT("Young Adult", true),
    CHILDRENS_LIT("Children's Literature", true),
    ;

    @Getter
    private final String displayName;
    @Getter
    private final boolean isFiction;

    BookCategory(String displayName, boolean isFiction) {
        this.displayName = displayName;
        this.isFiction = isFiction;
    }

}
