<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
