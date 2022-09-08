<<<<<<< HEAD
package com.spring.dto;

import com.spring.entity.Sex;

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
	private String sex;
	
	private Long breedId;
	private Long animalId;
	private Long categoryId;
	

}
=======
package com.spring.dto;

import com.spring.entity.Sex;

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
	private String sex;
	
	private Long breedId;
	private Long animalId;
	private Long categoryId;
	

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
