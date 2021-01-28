package com.javid.springframework.sfgpetclinicintellij.services.map;


import com.javid.springframework.sfgpetclinicintellij.model.Vet;
import com.javid.springframework.sfgpetclinicintellij.services.SpecialtyService;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractServiceMap<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet entity) {
        super.delete(entity);
    }

    @Override
    public Vet save(Vet entity) {
        if (entity != null) {
            try {
                entity.getSpecialties().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        specialty.setId(specialtyService.save(specialty).getId());
                    }
                });
            } catch (NullPointerException e) {
                entity.setSpecialties(new HashSet<>());
            }

            return super.save(entity);

        } else {
            throw new NullEntityException("entity cannot be null");
        }
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
