package com.javid.springframework.sfgpetclinicintellij.repositories;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {
    Optional<Pet> findFirstByNameAndOwner_Id(String name, Long ownerId);
}
