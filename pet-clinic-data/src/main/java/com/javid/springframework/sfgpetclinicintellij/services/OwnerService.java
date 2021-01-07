package com.javid.springframework.sfgpetclinicintellij.services;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();

    Set<Owner> findByLastName(String lastName);

}
