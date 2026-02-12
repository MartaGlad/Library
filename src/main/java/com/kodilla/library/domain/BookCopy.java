package com.kodilla.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "bookCopy", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopy bookCopy)) return false;
        return this.id != null && this.id.equals(bookCopy.id);
    }

    @Override
    public int hashCode(){
        return this.id != null ? this.id.hashCode() : 0;
    }

    public void setBook(Book book) {
        if (book == null) return;
        this.book = book;
        if(!book.getBookCopies().contains(this)) {
            book.getBookCopies().add(this);
        }
    }

    public void markAsDamaged() {
        this.status = Status.DAMAGED;
    }

    public void markAsLost() {
        this.status = Status.LOST;
    }

    public void markAsAvailable() {
        this.status = Status.AVAILABLE;
    }

    public void markAsRented() {
        this.status = Status.RENTED;
    }

}
