package com.petStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petStore.Entity.Pet;

public interface PetRepository extends JpaRepository<Pet,Long>{

}
