package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showOwner(@PathVariable("ownerId") Long id) {
        return new ModelAndView("owners/details")
                .addObject(ownerService.findById(id));
    }
}
