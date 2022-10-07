package com.vetsys.System.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specie")
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =
            "specie_id")
    private int id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL)
    private Set<Breed> breeds = new HashSet<>();

    @OneToMany(mappedBy ="specie", cascade = CascadeType.ALL)
    private Set<Pet> pets =
            new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
        for (Pet pet: this.pets) {
            pet.setSpecie(this);
        }
    }

    public Set<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(Set<Breed> breeds) {
        this.breeds = breeds;
        for (Breed breed: this.breeds) {
            breed.setSpecie(this);
        }
    }

}
