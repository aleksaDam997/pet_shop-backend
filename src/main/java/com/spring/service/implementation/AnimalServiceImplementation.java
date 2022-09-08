package com.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditAnimalDto;
import com.spring.entity.Animal;
import com.spring.repository.AnimalRepository;
import com.spring.repository.PetCategoryRepository;
import com.spring.service.AnimalService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class AnimalServiceImplementation implements AnimalService{

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private PetCategoryRepository petCategoryrep;
	
	@Override
	public Animal saveAnimal(AddEditAnimalDto addEditAnimalDto) {

		Animal animal = new Animal();
		
		animal.setName(addEditAnimalDto.getName());
		animal.setClas(addEditAnimalDto.getClas());
		animal.setPetCategory(this.petCategoryrep.findById(addEditAnimalDto.getPetCategoryId()).get());
		
		return this.animalRepository.save(animal);
	}

	@Override
	public List<Animal> getAllAnimals() {

		return this.animalRepository.findAll();
	}

	@Override
	public Animal getAnimal(Long animalId) {
		
		return this.animalRepository.findById(animalId).get();
	}

	@Override
	public List<Animal> getAnimalByCatId(Long categoryId) {
		
		return this.animalRepository.getAnimalByCategoryId(categoryId);
	}

}
