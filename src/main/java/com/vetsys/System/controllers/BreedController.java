package com.vetsys.System.controllers;

import com.vetsys.System.entities.Appointment;
import com.vetsys.System.entities.Breed;
import com.vetsys.System.repositories.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/breed")
public class BreedController {

    @Autowired
    private BreedRepository breedRepository;

    @GetMapping
    public ResponseEntity<Collection<Breed>> getBreeds(){
        List<Breed> data = breedRepository.findAll();
        if(data.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/specie/{id}")
    public ResponseEntity<Page<Breed>> getBreedsBySpecie(@PathVariable(name = "id") Integer id, Pageable page){
        return ResponseEntity.ok(breedRepository.findBySpecieId(id, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Breed> getBreed(@PathVariable Integer id){
        Breed breed = breedRepository.findById(id).orElseThrow();
        if(breed != null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveBreed(@RequestBody Breed breed){
        Breed data = breedRepository.save(breed);
        if(data != null){
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreed(@PathVariable Integer id){
        Breed result = breedRepository.findById(id).orElseThrow();
        if(result == null) return ResponseEntity.notFound().build();

        breedRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
