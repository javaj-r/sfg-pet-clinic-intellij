package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Javid on 2/27/2021.
 */

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @InjectMocks
    PetController controller;

    MockMvc mockMvc;
    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        owner = Owner.builder().id(1L).build();
        petTypes = Set.of(new PetType(1L, "Dog"), new PetType(2L, "Cat"));

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
    }

    @Test
    void getPetCreateForm() throws Exception {
        // given
        // when
        mockMvc.perform(get("/owners/1/pets/new"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("pets/form"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", hasProperty("new")));
    }

    @Test
    void processPetCreateForm() throws Exception {
        // given
        when(petService.save(any())).thenReturn(Pet.builder().id(2L).owner(owner).build());
        // when
        mockMvc.perform(post("/owners/1/pets/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(petService).save(any());
    }

    @Test
    void getPetUpdateForm() throws Exception {
        // given
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
        // when
        mockMvc.perform(get("/owners/1/pets/2/edit"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("pets/form"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", hasProperty("id")));
    }

    @Test
    void processPetUpdateForm() throws Exception {
        // given
        when(petService.save(any())).thenReturn(Pet.builder().id(2L).owner(owner).build());
        // when
        mockMvc.perform(post("/owners/1/pets/2/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
        verify(petService).save(any());
    }
}
