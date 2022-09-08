<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
