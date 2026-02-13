package com.kodilla.library.domain;

import com.kodilla.library.exception.BookCopyChangeStatusException;
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

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

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
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : 0;
    }


    public void setBook(Book book) {

        if (book == null) return;

        this.book = book;

        if(!book.getBookCopies().contains(this)) {
            book.getBookCopies().add(this);
        }
    }


    private boolean isChangeStatusAllowed(Status newStatus) {

        return switch (this.status) {
            case AVAILABLE -> newStatus == Status.RENTED || newStatus == Status.DAMAGED || newStatus == Status.LOST;
            case RENTED -> newStatus == Status.AVAILABLE || newStatus == Status.DAMAGED || newStatus == Status.LOST;
            case DAMAGED, LOST -> false;
        };
    }


    public void changeStatus(Status newStatus) {

        if (newStatus == null) throw new IllegalArgumentException("New status can't be null");

        if (newStatus == this.status) return;

        if (!isChangeStatusAllowed(newStatus)) throw new BookCopyChangeStatusException(this.status, newStatus);

        this.status = newStatus;
    }


}
