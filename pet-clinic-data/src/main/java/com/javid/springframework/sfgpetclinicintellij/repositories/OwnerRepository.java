package com.javid.springframework.sfgpetclinicintellij.repositories;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Iterable<Owner> findByLastName(String lastName);

    Optional<Owner> findOneByLastName(String lastName);

    Iterable<Owner> findByLastNameLike(String lastName);
}
