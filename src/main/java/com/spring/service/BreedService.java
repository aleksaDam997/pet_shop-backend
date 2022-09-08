<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
