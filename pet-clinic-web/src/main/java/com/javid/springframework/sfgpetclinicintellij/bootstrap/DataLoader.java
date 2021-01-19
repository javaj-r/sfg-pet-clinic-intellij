package com.javid.springframework.sfgpetclinicintellij.bootstrap;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.model.Person;
import com.javid.springframework.sfgpetclinicintellij.model.Vet;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import com.javid.springframework.sfgpetclinicintellij.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 =  setPersonParam(new Owner(), 1L, "Michael", "Weston");
        ownerService.save(owner1);

        Owner owner2 =  setPersonParam(new Owner(), 2L, "Fiona", "Glen");
        ownerService.save(owner2);

        System.out.println("Loaded Owners........");

        Vet vet1 = setPersonParam(new Vet(), 1L, "Sam", "Axe");
        vetService.save(vet1);

        Vet vet2 = setPersonParam(new Vet(), 2L, "Alex", "Owens");
        vetService.save(vet2);

        System.out.println("Loaded Vets..........");

    }

    private <T extends Person> T setPersonParam(T t, long id, String fName, String lName) {
        t.setId(id);
        t.setFirstName(fName);
        t.setLastName(lName);
        return t;
    }

}
