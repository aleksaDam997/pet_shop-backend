package com.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Pet;
import com.spring.entity.PetCategory;

public interface PetCategoryRepository extends JpaRepository<PetCategory, Long> {


}
