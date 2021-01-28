package com.javid.springframework.sfgpetclinicintellij.bootstrap;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.model.Person;
import com.javid.springframework.sfgpetclinicintellij.model.PetType;
import com.javid.springframework.sfgpetclinicintellij.model.Vet;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = petTypeService.save(setPetType("Dog"));

        PetType cat = petTypeService.save(setPetType("Cat"));

        PetType hamster = petTypeService.save(setPetType("Hamster"));

        PetType canary = petTypeService.save(setPetType("Canary"));

        System.out.println("Loaded PetTypes......");

        Owner owner1 =  setPersonParam(new Owner(), "Michael", "Weston");
        ownerService.save(owner1);

        Owner owner2 =  setPersonParam(new Owner(), "Fiona", "Glen");
        ownerService.save(owner2);

        System.out.println("Loaded Owners........");

        Vet vet1 = setPersonParam(new Vet(), "Sam", "Axe");
        vetService.save(vet1);

        Vet vet2 = setPersonParam(new Vet(), "Alex", "Owens");
        vetService.save(vet2);

        System.out.println("Loaded Vets..........");

    }

    private <T extends Person> T setPersonParam(T t, String fName, String lName) {
        t.setFirstName(fName);
        t.setLastName(lName);
        return t;
    }

    private PetType setPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petType;
    }

}
