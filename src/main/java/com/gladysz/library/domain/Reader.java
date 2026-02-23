package com.gladysz.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Setter
    @NotBlank
    @Size(max = 100)
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Setter
    @NotNull
    @Column(name = "date_of_account_creation", nullable = false)
    private LocalDate dateOfAccountCreation;

    @OneToMany(mappedBy = "reader", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Reader reader)) return false;
        return this.id != null && this.id.equals(reader.id);
    }


    @Override
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : 0;
    }


    public void addRent(Rent rent) {

        if (rent == null) return;

        if (!this.rents.contains(rent)) {
            this.rents.add(rent);
        }

        if (rent.getReader() != this) {
            rent.setReader(this);
        }

    }
}

