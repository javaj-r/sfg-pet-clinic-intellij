package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
        owners = Set.of(Owner.builder().id(1L).build(),
                Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        // when
        mockMvc.perform(get("/owners/find"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(service);
    }

    @Test
    void findOwnersFoundMany() throws Exception {
        // given
        when(service.findByLastNameLike(anyString())).thenReturn(owners);
        // when
        mockMvc.perform(get("/owners")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/list"))
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwnersFoundOne() throws Exception {
        // given
        when(service.findByLastNameLike(anyString())).thenReturn(Set.of(Owner.builder().id(1L).build()));
        // when
        mockMvc.perform(get("/owners")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void findOwnersNotFound() throws Exception {
        // given
        when(service.findByLastNameLike(anyString())).thenReturn(Set.of());
        // when
        mockMvc.perform(get("/owners")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", instanceOf(Owner.class)));
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