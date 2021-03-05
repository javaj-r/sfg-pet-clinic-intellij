package com.javid.springframework.sfgpetclinicintellij.controllers;

import com.javid.springframework.sfgpetclinicintellij.model.Vet;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping()
@Controller
public class VetController {

    private final VetService vetService;

    @RequestMapping({"vets", "vets.html", "vets/index", "vets/index.html"})
    public String vets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @GetMapping({"api/vets", "api/vets.json"})
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}
