package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractServiceMap<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PetType save(PetType entity) {
        return super.save(entity);
    }

    @Override
    public void delete(PetType entity) {
        super.delete(entity);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
