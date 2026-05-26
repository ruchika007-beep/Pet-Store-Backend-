package com.petStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petStore.Entity.Pet;
import com.petStore.Repository.PetRepository;

@Service
public class PetService {
	@Autowired
   private PetRepository petRepository;
   
	public Pet savePet(Pet pet) {
		return petRepository.save(pet);
	}
		public List<Pet> getAllPets(){
			return petRepository.findAll();
		}
}
