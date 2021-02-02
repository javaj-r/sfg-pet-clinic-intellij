package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("vets")
@Controller
public class VetController {

    private final VetService vetService;

    @RequestMapping({"", "/", "index", "index.html"})
    public String vets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
