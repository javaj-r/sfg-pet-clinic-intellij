package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.repositories.PetTypeRepository;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring_data_jpa")
public class PetTypeJpaService implements PetTypeService {

    private final PetTypeRepository repository;

    public PetTypeJpaService(PetTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> types = new HashSet<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public PetType findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(PetType entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
