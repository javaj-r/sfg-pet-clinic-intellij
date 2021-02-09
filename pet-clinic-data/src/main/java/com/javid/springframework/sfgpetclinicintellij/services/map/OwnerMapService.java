package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

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
                        pet.setType(petTypeService.save(pet.getType()));
                    } else if (pet.getType() == null) {
                        throw new NullEntityException("Pet Type is required!");
                    }
                    if (pet.getId() == null) {
                        pet.setId(petService.save(pet).getId());
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

        return lastName != null ?
                super.findAll().stream()
                        .filter(owner -> owner.getLastName() != null)
                        .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                        .collect(Collectors.toSet())
                :
                super.findAll().stream()
                        .filter(owner -> owner.getLastName() == null)
                        .collect(Collectors.toSet());
    }

    @Override
    public Owner findOneByLastName(String lastName) {

        return lastName != null ?
                super.findAll().stream()
                        .filter(owner -> owner.getLastName() != null)
                        .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                        .findFirst()
                        .orElse(null)
                :
                super.findAll().stream()
                        .filter(owner -> owner.getLastName() == null)
                        .findFirst()
                        .orElse(null);
    }
}
