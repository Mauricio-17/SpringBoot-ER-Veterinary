package com.vetsys.System.repositories;

import com.vetsys.System.entities.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Integer>
{
    Page<Breed> findBySpecieId(Integer specieId, Pageable page);
}
