package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.model.Pet;
import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by Javid on 2/27/2021.
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;
    private static final String PETS_FORM = "pets/form";

    @ModelAttribute("petTypes")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("pets/new")
    public String getPetCreateForm(Owner owner, Model model) {
        var pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return PETS_FORM;
    }

    @PostMapping("pets/new")
    public String processPetCreateForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        pet.setOwner(owner);

        if (petService.isExists(pet)) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return PETS_FORM;
        }

        owner.addPets(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("pets/{petId}/edit")
    public String getPetUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));

        return PETS_FORM;
    }

    @PostMapping("pets/{petId}/edit")
    public String processPetUpdateForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return PETS_FORM;
        } else {
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
