package com.spring.service;

import java.util.List;

import com.spring.dto.AddEditAnimalDto;
import com.spring.entity.Animal;

public interface AnimalService {

	public Animal saveAnimal(AddEditAnimalDto animal);
	public List<Animal> getAllAnimals();
	public Animal getAnimal(Long animalId);
	public List<Animal> getAnimalByCatId(Long categoryId);
}
