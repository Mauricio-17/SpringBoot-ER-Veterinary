package com.vetsys.System.repositories;

import com.vetsys.System.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends
        JpaRepository<Appointment, Integer>
{
}
