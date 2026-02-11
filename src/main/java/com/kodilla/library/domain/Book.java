package com.kodilla.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @Setter
    @NotBlank
    @Length(max = 100)
    @Column(name = "author", nullable = false)
    private String author;

    @Setter
    @Column(name = "year_of_release", nullable = false)
    private int yearOfRelease;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookCopy> bookCopies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return this.id != null && this.id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
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

