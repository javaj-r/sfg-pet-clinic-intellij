package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.repositories.OwnerRepository;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring_data_jpa")
public class OwnerJpaService implements OwnerService {

    private final OwnerRepository repository;

    public OwnerJpaService(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        repository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Owner entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        Set<Owner> owners = new HashSet<>();
        repository.findByLastName(lastName).forEach(owners::add);
        return owners;
    }
}
