<<<<<<< HEAD
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
public class Breed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long breedId;
	
	private String name;
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "animal_id", referencedColumnName = "animalId")
	private Animal animal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "breed")
	private Set<Pet> pet = new HashSet<>();
}
=======
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
public class Breed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long breedId;
	
	private String name;
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "animal_id", referencedColumnName = "animalId")
	private Animal animal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "breed")
	private Set<Pet> pet = new HashSet<>();
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
