package com.vetsys.System.controllers;

import com.vetsys.System.entities.Appointment;
import com.vetsys.System.entities.Service;
import com.vetsys.System.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public ResponseEntity<Collection<Appointment>> getAppointments(){
        List<Appointment> data = appointmentRepository.findAll();
        if (data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Integer id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        if (appointment != null){
            return new ResponseEntity<>(appointmentRepository.findById(id).orElseThrow(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveAppointment(@Valid @RequestBody Appointment appointment){
        
        Appointment savedAppointment = appointmentRepository.save(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAppointment.getId()).toUri();
        return ResponseEntity.created(location).body(savedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        if(appointment == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        appointmentRepository.delete(appointment);
        return ResponseEntity.ok().build();
    }

}
