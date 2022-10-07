package com.vetsys.System.repositories;

import com.vetsys.System.entities.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    Page<Pet> findByUserId(Integer userId, Pageable page);
    Page<Pet> findBySpecieId(Integer specieId, Pageable page);

}
