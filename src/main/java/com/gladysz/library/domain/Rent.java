package com.gladysz.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @Column(name = "date_of_rent",  nullable = false)
    private LocalDate dateOfRent;

    @Setter
    @Column(name = "date_of_return")
    private LocalDate dateOfReturn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if(!(o instanceof Rent rent)) return false;
        return this.id != null && this.id.equals(rent.id);
    }


    @Override
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : 0;
    }


    public void setReader(Reader reader) {

        if (reader == null) return;

        this.reader = reader;

        if(!reader.getRents().contains(this)) {
            reader.getRents().add(this);
        }
    }


    public void setBookCopy(BookCopy bookCopy) {

        if (bookCopy == null) return;

        this.bookCopy = bookCopy;

        if (!bookCopy.getRents().contains(this)) {
            bookCopy.getRents().add(this);
        }
    }


    public void completeRent() {

        this.dateOfReturn = LocalDate.now();
    }
}


