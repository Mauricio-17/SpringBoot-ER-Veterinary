package com.vetsys.System.repositories;

import com.vetsys.System.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieRepository extends JpaRepository<Specie, Integer> {
}
