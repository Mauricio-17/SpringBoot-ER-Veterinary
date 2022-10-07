package com.vetsys.System.controllers;

import com.vetsys.System.entities.User;
import com.vetsys.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    private ResponseEntity<Collection<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userRepository.findById(id).orElseThrow();
        if(user == null) return ResponseEntity.unprocessableEntity().build();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        System.out.println("Hello");
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        User result = userRepository.findById(id).orElseThrow();
        if (result ==null) return ResponseEntity.unprocessableEntity().build();

        userRepository.delete(result);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
