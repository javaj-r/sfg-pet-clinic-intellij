package com.javid.springframework.sfgpetclinicintellij.repositories;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
