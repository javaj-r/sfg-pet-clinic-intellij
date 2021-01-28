package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractServiceMap<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner entity) {
        super.delete(entity);
    }

    @Override
    public Owner save(Owner entity) {
        if (entity != null) {
            try {
                entity.getPets().forEach(pet -> {
                    if (pet.getType() != null && pet.getType().getId() == null) {
                        petTypeService.save(pet.getType());
                    } else if (pet.getType() == null) {
                        throw new NullEntityException("Pet Type is required!");
                    }
                });
            } catch (NullPointerException e) {
                entity.setPets(new HashSet<>());
            }

            return super.save(entity);

        } else {
            throw new NullEntityException("entity cannot be null");
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        return null;
    }
}
