package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {

}
