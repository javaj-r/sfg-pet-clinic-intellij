package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Visit;
import com.javid.springframework.sfgpetclinicintellij.repositories.VisitRepository;
import com.javid.springframework.sfgpetclinicintellij.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("spring_data_jpa")
public class VisitJpaService implements VisitService {

    private final VisitRepository repository;

    public VisitJpaService(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Visit entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
