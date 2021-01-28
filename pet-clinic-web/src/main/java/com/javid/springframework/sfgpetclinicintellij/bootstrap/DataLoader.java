package com.javid.springframework.sfgpetclinicintellij.bootstrap;

import com.javid.springframework.sfgpetclinicintellij.model.*;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.PetTypeService;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

        Owner owner1 = setOwner("Michael", "Weston",
                "123 Jones St", "Miami", "1231231234");
        Pet mikesDog = setPet(LocalDate.now(), dog, owner1, "Rosco");
        owner1.getPets().add(mikesDog);
        ownerService.save(owner1);

        Owner owner2 = setOwner("Fiona", "Glen",
                "258 Hagen St", "Miami", "9879879510");
        Pet fionasCat = setPet(LocalDate.now(), cat, owner2, "Black Cat");
        owner2.getPets().add(fionasCat);
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

    private Owner setOwner(String fName, String lName, String address, String city, String phone) {
        Owner owner = setPersonParam(new Owner(), fName, lName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(phone);
        return owner;
    }

    private PetType setPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petType;
    }

    private Pet setPet(LocalDate birthDate, PetType type, Owner owner, String name) {
        Pet pet = new Pet();
        pet.setBirthDate(birthDate);
        pet.setType(type);
        pet.setOwner(owner);
        pet.setName(name);
        return pet;
    }
}
