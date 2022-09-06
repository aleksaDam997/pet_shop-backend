package com.spring.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.spring.entity.Breed;
import com.spring.entity.Photo;
import com.spring.entity.Sex;
import com.spring.entity.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetDto {

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
	private int cartQuantity;
	
	private double vendorPrice;
	private double retailPrice;
	private double discount;
	
	private Status status;
	
	private Breed breed;
	
	private Set<Photo> photos = new HashSet<>();
}