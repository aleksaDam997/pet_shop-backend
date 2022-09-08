<<<<<<< HEAD
package com.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	@Query("SELECT p FROM PetCategory pc JOIN pc.animals a JOIN a.breed b JOIN b.pet p WHERE pc.petCategoryId = ?1")
	public Set<Pet> searchPetsByCategoryId(Long cartId);
	
	@Query("SELECT p FROM Pet p WHERE p.name LIKE %:word% OR p.description LIKE %:word% OR p.excerpt LIKE %:word% OR p.color LIKE %:word%")
	public Set<Pet> searchPetsByWord(String word);
	
	@Query("SELECT p FROM Animal a JOIN a.breed b JOIN b.pet p WHERE a.animalId = ?1 ")
	public Set<Pet> searchPetsByAnimalId(Long animalId);
	
	@Query("SELECT p FROM Breed b JOIN b.pet p WHERE b.breedId = ?1")
	public Set<Pet> searchPetsByBreedId(Long breedId);
	
	@Query("SELECT p FROM Cart c JOIN c.cartItem ci JOIN ci.pet p WHERE c.cartId = ?1")
	public Set<Pet> findBycartId(Long cartId);

}
=======
package com.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	@Query("SELECT p FROM PetCategory pc JOIN pc.animals a JOIN a.breed b JOIN b.pet p WHERE pc.petCategoryId = ?1")
	public Set<Pet> searchPetsByCategoryId(Long cartId);
	
	@Query("SELECT p FROM Pet p WHERE p.name LIKE %:word% OR p.description LIKE %:word% OR p.excerpt LIKE %:word% OR p.color LIKE %:word%")
	public Set<Pet> searchPetsByWord(String word);
	
	@Query("SELECT p FROM Animal a JOIN a.breed b JOIN b.pet p WHERE a.animalId = ?1 ")
	public Set<Pet> searchPetsByAnimalId(Long animalId);
	
	@Query("SELECT p FROM Breed b JOIN b.pet p WHERE b.breedId = ?1")
	public Set<Pet> searchPetsByBreedId(Long breedId);
	
	@Query("SELECT p FROM Cart c JOIN c.cartItem ci JOIN ci.pet p WHERE c.cartId = ?1")
	public Set<Pet> findBycartId(Long cartId);

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
