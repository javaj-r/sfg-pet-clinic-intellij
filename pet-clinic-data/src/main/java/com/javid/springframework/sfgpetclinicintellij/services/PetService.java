package com.javid.springframework.sfgpetclinicintellij.services;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;

public interface PetService extends CrudService<Pet, Long> {

    boolean isExists(Pet pet);
}
