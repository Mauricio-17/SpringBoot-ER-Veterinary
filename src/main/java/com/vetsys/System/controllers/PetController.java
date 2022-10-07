package com.vetsys.System.controllers;

import com.vetsys.System.entities.Pet;
import com.vetsys.System.entities.Specie;
import com.vetsys.System.entities.User;
import com.vetsys.System.repositories.PetRepository;
import com.vetsys.System.repositories.SpecieRepository;
import com.vetsys.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecieRepository specieRepository;

    @GetMapping
    public ResponseEntity<Collection<Pet>> getPets(){
        return new ResponseEntity<>(petRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Pet>> getPetsByUser(@PathVariable(value = "id") Integer id, Pageable page){
        return ResponseEntity.ok(petRepository.findByUserId(id, page));
    }
    @GetMapping("/specie/{id}")
    public ResponseEntity<Page<Pet>> getPetsBySpecie(@PathVariable(value = "id") Integer id, Pageable page){
        return ResponseEntity.ok(petRepository.findByUserId(id, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Integer id){
        Pet pet = petRepository.findById(id).orElseThrow(); // or it can use
        // Optional<Pet> with no orElseThrow()
        if (pet != null) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> savePet(@Valid @RequestBody Pet pet){
        System.out.println(pet.getSpecie().getId()+" "+pet.getBreed().getId());
        User user = userRepository.findById(pet.getUser().getId()).orElseThrow();
        Specie specie =
                specieRepository.findById(pet.getSpecie().getId()).orElseThrow();

        if(user == null || specie == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        pet.setUser(user);
        pet.setSpecie(specie);
        Pet savedPet = petRepository.save(pet);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPet.getId()).toUri();
        return ResponseEntity.created(location).body(savedPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet,
                                         @PathVariable Integer id){
        User user = userRepository.findById(pet.getUser().getId()).orElseThrow();
        Pet dataPet = petRepository.findById(id).orElseThrow();
        if(user == null || dataPet == null ){
            return ResponseEntity.unprocessableEntity().build();
        }
        Specie specie =
                specieRepository.findById(pet.getSpecie().getId()).orElseThrow();
        if(specie == null){
            return ResponseEntity.unprocessableEntity().build();
        }
        pet.setUser(user);
        pet.setSpecie(specie);
        pet.setId(dataPet.getId());
        this.petRepository.save(pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable Integer id){
        Pet pet = petRepository.findById(id).orElseThrow();
        if(pet == null){
            return ResponseEntity.notFound().build();
        }
        petRepository.delete(pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
