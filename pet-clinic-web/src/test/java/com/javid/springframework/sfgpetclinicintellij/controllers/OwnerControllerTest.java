package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listOwners() throws Exception {
        // given
        when(service.findAll()).thenReturn(owners);
        // when
        mockMvc.perform(get("/owners"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {
        // given
        when(service.findAll()).thenReturn(owners);
        // when
        mockMvc.perform(get("/owners/index"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        // when
        mockMvc.perform(get("/owners/find"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

        verifyNoInteractions(service);
    }

    @Test
    void showOwner() throws Exception {
        // given
        when(service.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        // when
        mockMvc.perform(get("/owners/1"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/details"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", notNullValue()))
                .andExpect(model().attribute("owner", instanceOf(Owner.class)))
                .andExpect(model().attribute("owner", hasProperty("id", equalTo(1L))));
    }
}