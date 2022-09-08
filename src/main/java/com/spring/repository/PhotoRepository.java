
package com.spring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	@Query("SELECT p FROM Photo p WHERE p.pet.petId = ?1")
	public Set<Photo> findByPetPetId(Long petId);
	
//	public Set<Photo> findByUserId(Long userId);
//	public Set<Photo> findByPetProductId(Long productId);
}
