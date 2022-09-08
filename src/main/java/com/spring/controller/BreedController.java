<<<<<<< HEAD
package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddEditBreedDto;
import com.spring.entity.Breed;
import com.spring.service.implementation.BreedServiceImplementation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BreedController {

	@Autowired
	private BreedServiceImplementation breedService;
	
	@PostMapping("api/admin/add/breed")
	public Breed saveBreed(@RequestBody AddEditBreedDto breed) {
		return this.breedService.saveBreed(breed);
	}
	
	@GetMapping("api/user/get/breed")
	public List<Breed> getAllBreeds() {
		return this.breedService.getAllBreeds();
	}
	
	@GetMapping("api/user/get/breed/{id}")
	public Breed getSpecificBreed(@PathVariable("id") Long breedId) {
		return this.breedService.getBreed(breedId);
	}
	
	@GetMapping("api/user/get/breed/byAnimalId/{id}")
	public List<Breed> getBreedsByAnimalId(@PathVariable("id") Long animalId) {
		return this.breedService.getBreedsByAnimalId(animalId);
	}
}
=======
package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddEditBreedDto;
import com.spring.entity.Breed;
import com.spring.service.implementation.BreedServiceImplementation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BreedController {

	@Autowired
	private BreedServiceImplementation breedService;
	
	@PostMapping("api/admin/add/breed")
	public Breed saveBreed(@RequestBody AddEditBreedDto breed) {
		return this.breedService.saveBreed(breed);
	}
	
	@GetMapping("api/user/get/breed")
	public List<Breed> getAllBreeds() {
		return this.breedService.getAllBreeds();
	}
	
	@GetMapping("api/user/get/breed/{id}")
	public Breed getSpecificBreed(@PathVariable("id") Long breedId) {
		return this.breedService.getBreed(breedId);
	}
	
	@GetMapping("api/user/get/breed/byAnimalId/{id}")
	public List<Breed> getBreedsByAnimalId(@PathVariable("id") Long animalId) {
		return this.breedService.getBreedsByAnimalId(animalId);
	}
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
