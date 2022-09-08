<<<<<<< HEAD
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Breed;
import com.spring.entity.Cart;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

	@Query("SELECT b FROM Breed b WHERE b.animal.animalId = ?1")
	public List<Breed> getBreedByAnimalId(Long animalId);
}
=======
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Breed;
import com.spring.entity.Cart;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

	@Query("SELECT b FROM Breed b WHERE b.animal.animalId = ?1")
	public List<Breed> getBreedByAnimalId(Long animalId);
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
