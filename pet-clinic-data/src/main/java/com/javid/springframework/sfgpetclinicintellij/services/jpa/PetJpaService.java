package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import com.javid.springframework.sfgpetclinicintellij.repositories.PetRepository;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring_data_jpa")
public class PetJpaService implements PetService {

    private final PetRepository repository;

    public PetJpaService(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        repository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Pet entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
