package com.kodilla.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
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
    @Length(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @NotBlank
    @Length(max = 100)
    @Column(name = "surname", nullable = false)
    private String surname;

    @Setter
    @NotNull
    @Column(name = "date_of_account_creation", nullable = false)
    private LocalDateTime dateOfAccountCreation;

    @OneToMany(mappedBy = "reader", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Rent> rents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Reader reader)) return false;
        return this.id != null && this.id.equals(reader.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addRent(Rent rent) {
        if(rent == null) return;

        if(!this.rents.contains(rent)) {
            this.rents.add(rent);
        }

        if(rent.getReader() != this) {
            rent.setReader(this);
        }

    }
}

