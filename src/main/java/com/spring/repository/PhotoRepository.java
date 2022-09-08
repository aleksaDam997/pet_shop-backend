<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
