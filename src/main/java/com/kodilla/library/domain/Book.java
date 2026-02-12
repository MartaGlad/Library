package com.kodilla.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Size(max = 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Setter
    @NotBlank
    @Size(max = 100)
    @Column(name = "author", nullable = false,  length = 100)
    private String author;

    @Setter
    @Positive
    @Column(name = "year_of_release", nullable = false)
    private int yearOfRelease;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopy> bookCopies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return this.id != null && this.id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    public void addBookCopy(BookCopy bookCopy) {
        if (bookCopy == null) return;

        if (!this.bookCopies.contains(bookCopy)) {
            this.bookCopies.add(bookCopy);
        }

        if (bookCopy.getBook() != this) {
            bookCopy.setBook(this);
        }
    }

}

