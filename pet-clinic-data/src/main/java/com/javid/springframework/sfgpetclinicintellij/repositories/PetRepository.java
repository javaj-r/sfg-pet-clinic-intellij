package com.javid.springframework.sfgpetclinicintellij.repositories;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
