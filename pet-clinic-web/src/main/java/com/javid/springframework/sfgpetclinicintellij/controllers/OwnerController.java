package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return "owners/find";
    }

    @GetMapping
    public String findOwners(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        var owners = ownerService.findByLastNameLike(owner.getLastName());

        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");

            return "owners/find";
        } else if (owners.size() == 1) {
            var id = owners.iterator().next().getId();

            return String.format("redirect:/owners/%s", id);
        } else {
            model.addAttribute("owners", owners);

            return "owners/list";
        }
    }

    @GetMapping({"{ownerId}"})
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        return new ModelAndView("owners/details")
                .addObject(ownerService.findById(ownerId));
    }

    @GetMapping("new")
    public String getOwnerCreateForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/form";
    }

    @PostMapping("new")
    public String processOwnerCreateForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/form";
        } else {
            var savedOwner = ownerService.save(owner);
            return String.format("redirect:/owners/%s", savedOwner.getId());
        }
    }

    @GetMapping("{ownerId}/edit")
    public String getOwnerUpdateForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/form";
    }

    @PostMapping("{ownerId}/edit")
    public String processOwnerUpdateForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owners/form";
        } else {
            owner.setId(ownerId);
            var savedOwner = ownerService.save(owner);
            return String.format("redirect:/owners/%s", savedOwner.getId());
        }
    }

}
