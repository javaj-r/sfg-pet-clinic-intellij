package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Javid on 2/28/2021.
 */

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getVisitCreateForm() throws Exception {
        // given
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
        // when
        mockMvc.perform(get("/owners/1/pets/2/visits/new"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("pets/visits/form"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attribute("visit", hasProperty("pet", notNullValue())));

        verify(petService).findById(anyLong());
    }

    @Test
    void processVisitCreateForm() throws Exception {
        // given
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
        // when
        mockMvc.perform(post("/owners/1/pets/2/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService).save(any());
    }

}