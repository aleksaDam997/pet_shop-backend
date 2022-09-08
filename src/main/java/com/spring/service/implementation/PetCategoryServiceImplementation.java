<<<<<<< HEAD
package com.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.PetCategory;
import com.spring.repository.PetCategoryRepository;
import com.spring.service.PetCategoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PetCategoryServiceImplementation implements PetCategoryService{

	@Autowired
	private PetCategoryRepository petCategoryRep;
	
	@Override
	public PetCategory savePetCategory(PetCategory category) {
		
		return this.petCategoryRep.save(category);
	}

	@Override
	public List<PetCategory> getAllPetCategories() {

		return this.petCategoryRep.findAll();
	}

	@Override
	public PetCategory getPetCategory(Long petCategoryId) {

		return this.petCategoryRep.findById(petCategoryId).get();
	}

}
=======
package com.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.PetCategory;
import com.spring.repository.PetCategoryRepository;
import com.spring.service.PetCategoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class PetCategoryServiceImplementation implements PetCategoryService{

	@Autowired
	private PetCategoryRepository petCategoryRep;
	
	@Override
	public PetCategory savePetCategory(PetCategory category) {
		
		return this.petCategoryRep.save(category);
	}

	@Override
	public List<PetCategory> getAllPetCategories() {

		return this.petCategoryRep.findAll();
	}

	@Override
	public PetCategory getPetCategory(Long petCategoryId) {

		return this.petCategoryRep.findById(petCategoryId).get();
	}

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
