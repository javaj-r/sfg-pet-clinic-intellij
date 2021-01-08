package com.javid.springframework.sfgpetclinicintellij.services;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Set<Owner> findByLastName(String lastName);
}
