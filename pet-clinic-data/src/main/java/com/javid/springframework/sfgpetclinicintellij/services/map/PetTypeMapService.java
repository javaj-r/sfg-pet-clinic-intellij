package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
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
