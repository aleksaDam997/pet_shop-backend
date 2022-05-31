package com.spring.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.spring.entity.Breed;
import com.spring.entity.CartItem;
import com.spring.entity.Photo;
import com.spring.entity.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddEditPetDto {

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
	
	private Long breedId;
	
	
}
