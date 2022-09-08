
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Cart;
import com.spring.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	@Query("SELECT pc FROM ProductCategory pc WHERE pc.animal.animalId = ?1")
	public List<ProductCategory> getAnimalByCategoryId(Long animalId);
}
