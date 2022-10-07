package com.vetsys.System.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.metamodel.SetAttribute;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =
            "appointment_id")
    private int id;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;
    private String description;

    @ManyToMany
    @JoinTable(name =
            "detail_appointment", joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "pet_id"))
    private Set<Pet> pets = new HashSet<>();

    @ManyToMany
    @JoinTable(name =
            "detail_service",
            joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
    private Set<Service> services = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
