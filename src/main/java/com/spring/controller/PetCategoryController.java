package com.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.PetCategory;
import com.spring.service.implementation.PetCategoryServiceImplementation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class PetCategoryController {

	private PetCategoryServiceImplementation petCategoryService;
	
	@PostMapping("api/admin/add/petCategory")
	public PetCategory savePetCategory(@RequestBody PetCategory petCategory) {
		return this.petCategoryService.savePetCategory(petCategory);
	}
	
	@GetMapping("api/user/get/categories")
	public List<PetCategory> getAllCategories() {
		return this.petCategoryService.getAllPetCategories();
	}
	
	@GetMapping("api/user/get/categories/{id}")
	public PetCategory getSpecificCategory(@PathVariable("id") Long petCatId) {
		return this.petCategoryService.getPetCategory(petCatId);
	}
}
