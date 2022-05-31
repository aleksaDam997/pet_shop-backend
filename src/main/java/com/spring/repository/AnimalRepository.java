package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
