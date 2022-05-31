package com.spring.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.entity.Breed;
import com.spring.entity.CartItem;
import com.spring.entity.Photo;
import com.spring.entity.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetWithPhotosDto {

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
	
	private Breed breed;

	private Set<Photo> photos = new HashSet<>();
	
	private Set<CartItem> cartItem = new HashSet<>();
}