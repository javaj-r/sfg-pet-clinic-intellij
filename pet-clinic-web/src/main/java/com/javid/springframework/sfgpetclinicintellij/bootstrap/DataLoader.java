package com.javid.springframework.sfgpetclinicintellij.bootstrap;

import com.javid.springframework.sfgpetclinicintellij.model.*;
import com.javid.springframework.sfgpetclinicintellij.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Override
    public void run(String... args) {

        if (petTypeService.findAll().isEmpty()) {
            new DataLoaderUtil().loadData();
        }

    }

    private class DataLoaderUtil {

        private void loadData() {

            val dog = petTypeService.save(setPetType("Dog"));
            val cat = petTypeService.save(setPetType("Cat"));
            val hamster = petTypeService.save(setPetType("Hamster"));
            val canary = petTypeService.save(setPetType("Canary"));

            log.debug("Loaded PetTypes......");

            val radiology = specialtyService.save(setSpecialty("Radiology"));
            val surgery = specialtyService.save(setSpecialty("Surgery"));
            val dentistry = specialtyService.save(setSpecialty("Dentistry"));

            log.debug("Loaded Specialties...");

            val owner1 = setOwner("Michael", "Weston", "123 Jones St", "Miami", "1231231234");
            val mikesDog = setPet(LocalDate.now(), dog, owner1, "Rosco");
            owner1.addPets(mikesDog);

            ownerService.save(owner1);

            val dogVisit = setVisit(LocalDate.now(), "Weak Dog", mikesDog);
            visitService.save(dogVisit);
            mikesDog.addVisits(dogVisit);

            val owner2 = setOwner("Fiona", "Glen", "258 Hagen St", "Miami", "9879879510");
            val fionasCat = setPet(LocalDate.now(), cat, owner2, "Black Cat");
            owner2.addPets(fionasCat);

            ownerService.save(owner2);

            val catVisit = setVisit(LocalDate.now(), "Sneezy Cat", fionasCat);
            visitService.save(catVisit);
            fionasCat.addVisits(catVisit);

            log.debug("Loaded Owners........");

            val vet1 = setVet("Sam", "Axe", radiology);
            vetService.save(vet1);

            val vet2 = setVet("Alex", "Owens", surgery);
            vetService.save(vet2);

            log.debug("Loaded Vets..........");

        }

        private Owner setOwner(String fName, String lName, String address, String city, String phone) {
            return Owner.builder()
                    .firstName(fName)
                    .lastName(lName)
                    .address(address)
                    .city(city)
                    .telephone(phone)
                    .build();
        }

        private Vet setVet(String fName, String lName, Specialty... specialties) {
            return Vet.builder()
                    .firstName(fName)
                    .lastName(lName)
                    .specialties(new HashSet<>(Arrays.asList(specialties)))
                    .build();
        }

        private PetType setPetType(String name) {
            return new PetType().setName(name);
        }

        private Pet setPet(LocalDate birthDate, PetType type, Owner owner, String name) {
            return Pet.builder()
                    .birthDate(birthDate)
                    .type(type)
                    .owner(owner)
                    .name(name)
                    .build();
        }

        private Specialty setSpecialty(String description) {
            return new Specialty().setDescription(description);
        }

        private Visit setVisit(LocalDate date, String description, Pet pet) {
            return Visit.builder()
                    .date(date)
                    .description(description)
                    .pet(pet)
                    .build();
        }
    }
}
