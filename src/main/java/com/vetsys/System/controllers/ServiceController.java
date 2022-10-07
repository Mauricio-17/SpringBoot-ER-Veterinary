package com.vetsys.System.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vetsys.System.entities.Service;
import com.vetsys.System.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public ResponseEntity<Collection<Service>> getServices(){
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getService(@PathVariable Integer id){
        Service service = serviceRepository.findById(id).orElseThrow();
        if(service == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service);
    }

    // Endpoint : "/api/service"
    @PostMapping
    public ResponseEntity<Service> saveService(@Valid @RequestBody Service service){

        Service savedService = serviceRepository.save(service);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedService.getId()).toUri();
        return ResponseEntity.created(location).body(savedService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@RequestBody Service service,
                                               @PathVariable Integer id){
        Service data = serviceRepository.findById(id).orElseThrow();
        if (data == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        service.setId(id);
        Service result = serviceRepository.save(service);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id){
        Service service = serviceRepository.findById(id).orElseThrow();
        if(service == null) return ResponseEntity.unprocessableEntity().build();

        serviceRepository.delete(service);
        return ResponseEntity.noContent().build();
    }

}
