package com.vetsys.System.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =
            "service_id")
    private int id;
    private String name;
    private String description;
    private float price;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "detail_service", joinColumns = @JoinColumn(name = "service_id",
            referencedColumnName = "service_id"), inverseJoinColumns =
            @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id"))
    private Set<Appointment> appointments = new HashSet<>();


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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(
            Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
