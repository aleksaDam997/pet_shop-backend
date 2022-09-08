package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddEditAnimalDto;
import com.spring.entity.Animal;
import com.spring.service.implementation.AnimalServiceImplementation;

@RestController
public class AnimalController {

	@Autowired
	private AnimalServiceImplementation animalService;
	
	@PostMapping("api/admin/add/animal")
	public Animal saveAnimal(@RequestBody AddEditAnimalDto animal) {
		return this.animalService.saveAnimal(animal);
	}
	
	@GetMapping("api/user/get/animal")
	public List<Animal> getAnimals() {
		return this.animalService.getAllAnimals();
	}
	
	@GetMapping("api/user/get/animal/{id}")
	public Animal getAnimalById(@PathVariable("id") Long animalId) {
		return this.animalService.getAnimal(animalId);
	}
	
	@GetMapping("api/user/get/animal/byCategoryId/{id}")
	public List<Animal> getAnimalByCategoryID(@PathVariable("id") Long categoryID) {
		return this.animalService.getAnimalByCatId(categoryID);
	}
}
