
package com.spring.service;

import java.util.List;

import com.spring.dto.AddEditBreedDto;
import com.spring.entity.Breed;

public interface BreedService {

	public Breed saveBreed(AddEditBreedDto breed);
	public List<Breed> getAllBreeds();
	public Breed getBreed(Long breedId);
	public List<Breed> getBreedsByAnimalId(Long animalId);
}
