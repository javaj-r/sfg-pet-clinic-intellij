package com.javid.springframework.sfgpetclinicintellij.repositories;

import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository  extends CrudRepository<PetType, Long> {
}
