package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerService ownerService;

    private final String aSmith = "Smith";
    private final String bSmith = "Smith";
    private final Long aId = 1L;
    private final Long bId = 2L;

    @BeforeEach
    void setUp() {
        ownerService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerService.save(Owner.builder().id(aId).lastName(aSmith).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(1L);
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(1L));
        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 3L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerService.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(aId);
        assertEquals(aId, owner.getId());
    }

    @Test
    void findByLastName() {
        ownerService.save(Owner.builder().id(bId).lastName(bSmith).build());

        Set<Owner> owners = ownerService.findByLastName(aSmith);
        Set<Long> idSet = owners.stream()
                .map(Owner::getId)
                .collect(Collectors.toSet());

        assertEquals(2, owners.size());
        assertTrue(idSet.contains(aId));
        assertTrue(idSet.contains(bId));
    }

    @Test
    void findByLastNameNotFound() {
        ownerService.save(Owner.builder().id(bId).lastName(bSmith).build());
        Set<Owner> owners = ownerService.findByLastName("NotSaved");

        assertEquals(0, owners.size());
    }

    @Test
    void findOneByLastName() {
        Owner owner = ownerService.findOneByLastName(aSmith);
        assertNotNull(owner);
        assertEquals(aId, owner.getId());
    }

    @Test
    void findOneByLastNameNotFound() {
        Owner owner = ownerService.findOneByLastName("NotSaved");
        assertNull(owner);
    }


    @Test
    void findByLastNameLik() {
        ownerService.save(Owner.builder().id(bId).lastName(bSmith).build());

        Set<Owner> owners = ownerService.findByLastNameLike("mit");
        Set<Long> idSet = owners.stream()
                .map(Owner::getId)
                .collect(Collectors.toSet());

        assertEquals(2, owners.size());
        assertTrue(idSet.contains(aId));
        assertTrue(idSet.contains(bId));
    }
}