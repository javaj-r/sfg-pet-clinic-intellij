package com.javid.springframework.sfgpetclinicintellij.services.jpa;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    static final String LAST_NAME = "Smith";
    static final String ALEN = "Alen";
    static final String BEN = "Ben";
    static final Long ALEN_ID = 1L;
    static final Long BEN_ID = 2L;


    Owner alenSmith;
    Owner benSmith;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @BeforeEach
    void setUp() {
        alenSmith = Owner.builder().id(ALEN_ID).firstName(ALEN).lastName(LAST_NAME).build();
        benSmith = Owner.builder().id(BEN_ID).firstName(BEN).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        // given
        Set<Owner> expectedOwners = new HashSet<>();
        expectedOwners.add(alenSmith);
        expectedOwners.add(benSmith);
        when(ownerRepository.findAll()).thenReturn(expectedOwners);
        // when
        Set<Owner> actualOwners = ownerJpaService.findAll();
        // then
        assertEquals(expectedOwners.size(), actualOwners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        // given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(alenSmith));
        // when
        Owner actualOwner = ownerJpaService.findById(ALEN_ID);
        // then
        assertNotNull(actualOwner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        // given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        // when
        Owner actualOwner = ownerJpaService.findById(ALEN_ID);
        // then
        assertNull(actualOwner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        // given
        when(ownerRepository.save(alenSmith)).thenReturn(alenSmith);
        when(ownerRepository.save(benSmith)).thenReturn(benSmith);
        // when
        Owner actualOwner = ownerJpaService.save(alenSmith);
        Owner actualOwner2 = ownerJpaService.save(benSmith);
        // then
        assertNotNull(actualOwner);
        assertNotNull(actualOwner2);
        verify(ownerRepository, times(2)).save(any());
    }

    @Test
    void delete() {
        // when
        ownerJpaService.delete(alenSmith);
        ownerJpaService.delete(benSmith);
        // then
        verify(ownerRepository).delete(alenSmith);
        verify(ownerRepository).delete(benSmith);
        verify(ownerRepository, times(2)).delete(any());
    }

    @Test
    void deleteById() {
        // when
        ownerJpaService.deleteById(ALEN_ID);
        ownerJpaService.deleteById(BEN_ID);
        // then
        verify(ownerRepository).deleteById(ALEN_ID);
        verify(ownerRepository).deleteById(BEN_ID);
        verify(ownerRepository, times(2)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        // given
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(alenSmith);
        returnOwners.add(benSmith);
        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwners);
        // when
        Set<Owner> owners = ownerJpaService.findByLastName(LAST_NAME);
        // then
        assertEquals(2, owners.size());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findOneByLastName() {
        // given
        when(ownerRepository.findOneByLastName(anyString())).thenReturn(Optional.of(alenSmith));
        // when
        Owner smith = ownerJpaService.findOneByLastName(LAST_NAME);
        // then
        assertNotNull(smith);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findOneByLastName(any());
    }

    @Test
    void findOneByLastNameNotExists() {
        // given
        final String lastName = "Not Exist";
        when(ownerRepository.findOneByLastName(lastName)).thenReturn(Optional.empty());
        // when
        Owner owner = ownerJpaService.findOneByLastName(lastName);
        // then
        assertNull(owner);
        verify(ownerRepository).findOneByLastName(any());
    }
}