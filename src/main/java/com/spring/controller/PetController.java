package com.spring.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddEditPetDto;
import com.spring.dto.PetSearchDto;
import com.spring.dto.PetDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Pet;
import com.spring.service.implementation.PetServiceImplementation;

@RestController
public class PetController {
	
	@Autowired
	private PetServiceImplementation petService;
	
	@PostMapping("api/admin/add/pet")
	public Pet addNewPet(@RequestBody AddEditPetDto pet) {
		return this.petService.savePet(pet);
	}
	
	@GetMapping("api/admin/get/pet")
	public List<PetDto> getAllPets() {
		return this.petService.getPets();
	}
	
	@GetMapping("api/user/get/pet/{id}/photos")
	public PetDto getPetById(@PathVariable("id") Long id) {
		return this.petService.getPetWithPhotos(id);
	}
	
	@GetMapping("api/user/get/pet/{id}")
	public Pet getPet(@PathVariable("id") Long id) {
		return this.petService.getPetById(id);
	}
	
	@GetMapping("api/user/get/pet/byPetCategory/{id}")
	public Set<Pet> petsByPetCategory(@PathVariable("id") Long id) {
		return this.petService.searchPetsByPetCategoryId(id);
	}
	
	@GetMapping("api/user/get/pet/byAnimal/{id}")
	public Set<Pet> petsByAnimal(@PathVariable("id") Long id) {
		return this.petService.searchPetsByAnimalId(id);
	}
	
	@GetMapping("api/user/get/pet/byBreed/{id}")
	public Set<Pet> petsByBreed(@PathVariable("id") Long id) {
		return this.petService.searchPetsByBreedId(id);
	}
	
	@PostMapping("api/user/get/pet/search")
	public Set<Pet> petsByWord(@RequestBody SearchDto search) {
		return this.petService.searchPetsByWord(search);
	}
	
	@PostMapping("api/user/pet/search")
	public List<PetDto> petFilterSearch(@RequestBody PetSearchDto petSearchDto) {
		return this.petService.searchForPets(petSearchDto);
	}
	
}
