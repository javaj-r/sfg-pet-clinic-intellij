package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet entity) {
        super.delete(entity);
    }

    @Override
    public Pet save(Pet entity) {
        return super.save(entity);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public boolean isExists(Pet pet) throws NullPointerException {
            final var name = pet.getName();
            final var ownerId = pet.getOwner().getId();
            return findAll().stream()
                    .filter(p -> name.equals(p.getName()))
                    .anyMatch(p -> ownerId.equals(p.getId()));
    }
}
