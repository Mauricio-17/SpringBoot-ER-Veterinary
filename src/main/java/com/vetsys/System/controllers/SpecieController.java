package com.vetsys.System.controllers;

import com.vetsys.System.entities.Specie;
import com.vetsys.System.repositories.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/specie")
public class SpecieController {

    @Autowired
    SpecieRepository specieRepository;

    @GetMapping
    public ResponseEntity<Collection<Specie>> getSpecies(){
        return new ResponseEntity<>(specieRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specie> getSpecie(@PathVariable Integer id){
        Specie specie = specieRepository.findById(id).orElseThrow();
        if(specie == null) return ResponseEntity.unprocessableEntity().build();

        return new ResponseEntity<>(specie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveSpecie(@Valid @RequestBody Specie specie){//
        // must contain the annotation @Valid
        System.out.println("Hello");
        Specie savedSpecie = specieRepository.save(specie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSpecie.getId()).toUri();
        return ResponseEntity.created(location).body(savedSpecie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specie> updateSpecie(@RequestBody Specie specie,
                                               @PathVariable Integer id){
        Specie data = specieRepository.findById(id).orElseThrow();
        if(data == null) return ResponseEntity.unprocessableEntity().build();

        specie.setId(id);
        Specie result = specieRepository.save(specie);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecie(@PathVariable Integer id){
        Specie specie = specieRepository.findById(id).orElseThrow();
        if (specie == null) return ResponseEntity.unprocessableEntity().build();

        specieRepository.delete(specie);
        return ResponseEntity.noContent().build();
    }

}
