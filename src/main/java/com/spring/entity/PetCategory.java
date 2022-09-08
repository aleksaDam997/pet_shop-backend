<<<<<<< HEAD
package com.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petCategoryId;
	
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "petCategory")
	private Set<Animal> animals = new HashSet<>();

}
=======
package com.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petCategoryId;
	
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "petCategory")
	private Set<Animal> animals = new HashSet<>();

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
