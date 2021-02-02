package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping({"", "/", "index", "index.html"})
    public String vets(Model model) {
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }
}
