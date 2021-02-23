package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping({"", "/", "index", "index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping({"find"})
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping({"{ownerId}"})
    public ModelAndView showOwner(@PathVariable("ownerId") Long id) {
        return new ModelAndView("owners/details")
                .addObject(ownerService.findById(id));
    }
}
