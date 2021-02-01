package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Specialty;
import com.javid.springframework.sfgpetclinicintellij.repositories.SpecialtyRepository;
import com.javid.springframework.sfgpetclinicintellij.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring_data_jpa")
public class SpecialtyJpaService implements SpecialtyService {

    private final SpecialtyRepository repository;

    public SpecialtyJpaService(SpecialtyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        repository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Specialty entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
