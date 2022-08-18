package com.spring.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.spring.entity.Sex;
import com.spring.entity.Status;

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
	private Status status;
	
	private Long breedId;
	
	
}
