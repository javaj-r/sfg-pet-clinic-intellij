package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Vet;
import com.javid.springframework.sfgpetclinicintellij.repositories.VetRepository;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Profile("spring_data_jpa")
public class VetJpaService implements VetService {

    private final VetRepository repository;

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        repository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Vet entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
