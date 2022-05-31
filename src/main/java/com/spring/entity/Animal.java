package com.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long animalId;
	private String name;
	private String clas;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_category_id", referencedColumnName = "petCategoryId")
	private PetCategory petCategory;
	
	@JsonIgnore
	@OneToMany(mappedBy = "animal")
	private Set<Breed> breed = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "animal")
	private Set<ProductCategory> productCategories = new HashSet<>();
	
}
