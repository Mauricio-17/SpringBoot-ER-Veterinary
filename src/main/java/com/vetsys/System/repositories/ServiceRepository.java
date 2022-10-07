package com.vetsys.System.repositories;

import com.vetsys.System.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
