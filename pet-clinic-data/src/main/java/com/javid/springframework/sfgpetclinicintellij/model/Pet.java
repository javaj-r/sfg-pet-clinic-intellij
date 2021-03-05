package com.javid.springframework.sfgpetclinicintellij.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, LocalDate birthDate, PetType type, Owner owner, String name, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.visits = visits != null ? visits : new HashSet<>();
    }

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet addVisits(Visit... visits) {
        this.visits.addAll(Arrays.asList(visits));
        return this;
    }
}
