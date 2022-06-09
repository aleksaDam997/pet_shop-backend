package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetSearchDto {
	
	private String keyWords;
	private double minPrice;
	private double maxPrice;
	private SortBy sortBy;
	
	private Long breedId;
	private Long animalId;
	private Long categoryId;
	

}
