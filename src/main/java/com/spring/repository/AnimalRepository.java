
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Animal;
import com.spring.entity.Cart;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

	@Query("SELECT a FROM Animal a WHERE a.petCategory.petCategoryId = ?1")
	public List<Animal> getAnimalByCategoryId(Long catId);
}
