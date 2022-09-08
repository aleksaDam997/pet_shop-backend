
package com.spring.service;

import java.util.List;

import com.spring.entity.Breed;
import com.spring.entity.PetCategory;

public interface PetCategoryService {

	public PetCategory savePetCategory(PetCategory category);
	public List<PetCategory> getAllPetCategories();
	public PetCategory getPetCategory(Long petCategoryId);
}
