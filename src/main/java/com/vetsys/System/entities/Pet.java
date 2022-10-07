package com.vetsys.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private int id;
    private String name;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date dateBorn;
    private boolean gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name =
            "specie_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Specie specie;

    @ManyToOne
    @JoinColumn(name ="breed_id")
    private Breed breed;

    @ManyToMany
    @JoinTable(name =
            "detail_appointment", joinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "pet_id"), inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id"))
    @JsonIgnore
    private Set<Appointment>
            appointments = new HashSet<>();

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

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(
            Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
