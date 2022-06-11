package com.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditBreedDto;
import com.spring.entity.Breed;
import com.spring.repository.AnimalRepository;
import com.spring.repository.BreedRepository;
import com.spring.service.BreedService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BreedServiceImplementation implements BreedService{

	@Autowired
	private BreedRepository breedRepository;
	@Autowired
	private AnimalRepository animalrepository;

	@Override
	public Breed saveBreed(AddEditBreedDto addEditBreedDto) {

		Breed breed = new Breed();
		breed.setName(addEditBreedDto.getName());
		breed.setDescription(addEditBreedDto.getDescription());
		
		breed.setAnimal(this.animalrepository.findById(addEditBreedDto.getAnimalId()).get());
		
		return this.breedRepository.save(breed);
	}

	@Override
	public List<Breed> getAllBreeds() {

		return this.breedRepository.findAll();
	}

	@Override
	public Breed getBreed(Long breedId) {

		return this.breedRepository.findById(breedId).get();
	}

	@Override
	public List<Breed> getBreedsByAnimalId(Long animalId) {
		
		return this.breedRepository.getBreedByAnimalId(animalId);
	}
	
}
