package com.spring.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.entity.CartItem;
import com.spring.entity.Photo;
import com.spring.entity.ProductCategory;
import com.spring.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddEditProductDto {

	private int code;
	private String name;
	private String details;
	
	private int quantity;
	
	private double vendorPrice;
	private double retailPrice;
	private double discount;
	
	private Status status;
	
	private Long productCategoryId;

	private Set<Photo> photos = new HashSet<>();
	
	private Set<CartItem> cartItem = new HashSet<>();
}
