package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Visit;
import com.javid.springframework.sfgpetclinicintellij.services.PetService;
import com.javid.springframework.sfgpetclinicintellij.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

/**
 * Created by Javid on 2/28/2021.
 */

@RequiredArgsConstructor
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        var pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        return Visit.builder().pet(pet).build();
    }

    @GetMapping("owners/*/pets/{petId}/visits/new")
    public String getVisitCreateForm(@PathVariable Long petId, Model model) {
        return "pets/visits/form";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processVisitCreateForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/visits/form";
        } else {
            visit.getPet().addVisits(visit);
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
