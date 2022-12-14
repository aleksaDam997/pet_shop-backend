package com.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petId;
	
	private String name;
	private String description;
	private String excerpt;
	private int age;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	private String color;
	private String eyesColor;
	
	private int quantity;
	
	private double vendorPrice;
	private double retailPrice;
	private double discount;
	
	private Status status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "breed_id", referencedColumnName = "breedId")
	private Breed breed;

	@JsonIgnore
	@OneToMany(mappedBy = "pet")
	private Set<Photo> photos = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "pet")
	private Set<CartItem> cartItem = new HashSet<>();

	
}
