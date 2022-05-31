package com.spring.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.entity.Breed;
import com.spring.entity.PetCategory;
import com.spring.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddEditAnimalDto {

	private String name;
	private String clas;
	
	private Long petCategoryId;

	private Long breedId;
	
	private Long productCategoryId;
}
