package com.example.PetPalHub.Repositories;

import com.example.PetPalHub.Entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByShelterId(int shelterId);

}
