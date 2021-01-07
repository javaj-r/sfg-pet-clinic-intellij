package com.javid.springframework.sfgpetclinicintellij.services;

import com.javid.springframework.sfgpetclinicintellij.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();
}
