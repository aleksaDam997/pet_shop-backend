package com.spring.service;

import java.util.List;
import java.util.Set;

import com.spring.dto.AddEditPetDto;
import com.spring.dto.PetWithPhotosDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Pet;

public interface PetService {

	public Pet savePet(AddEditPetDto addEditPetDto);
	public Pet editPetData(Long petIde, AddEditPetDto editPetDto);
	public Pet getPetById(Long petId);
	public List<PetWithPhotosDto> getPets();
	
	//Custom QUERYS
	public Set<Pet> searchPetsByWord(SearchDto search);
	public Set<Pet> searchPetsByPetCategoryId(Long petCategoryId);
	public Set<Pet> searchPetsByBreedId(Long breedId);
	public Set<Pet> searchPetsByAnimalId(Long animalId);
}
