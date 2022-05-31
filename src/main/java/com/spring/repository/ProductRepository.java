package com.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Pet;
import com.spring.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM PetCategory pc JOIN pc.animals a JOIN a.productCategories c JOIN c.products p WHERE pc.petCategoryId = ?1")
	public Set<Product> searchPetsByPetCategoryId(Long cartId);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:word% OR p.details LIKE %:word%")
	public Set<Product> searchPetsByWord(String word);
	
	@Query("SELECT p FROM Animal a JOIN a.productCategories pc JOIN pc.products p WHERE a.animalId = ?1 ")
	public Set<Product> searchPetsByAnimalId(Long animalId);
	
	@Query("SELECT p FROM ProductCategory pc JOIN pc.products p WHERE pc.productCategoryId = ?1")
	public Set<Product> searchPetsByProductCategoryId(Long breedId);
}
