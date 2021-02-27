package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * Created by Javid on 2/27/2021.
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    @ModelAttribute("petTypes")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
