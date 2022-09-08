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
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petProductId;
	
	private int code;
	private String name;
	private String details;
	
	private int quantity;
	
	private double vendorPrice;
	private double retailPrice;
	private double discount;
	
	private Status status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_category_id", referencedColumnName = "productCategoryId")
	private ProductCategory productCategory;
	
	@JsonIgnore
	@OneToMany(mappedBy = "petProduct")
	private Set<Photo> photos = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "petProduct")
	private Set<CartItem> cartItem = new HashSet<>();
	
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
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long petProductId;
	
	private int code;
	private String name;
	private String details;
	
	private int quantity;
	
	private double vendorPrice;
	private double retailPrice;
	private double discount;
	
	private Status status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_category_id", referencedColumnName = "productCategoryId")
	private ProductCategory productCategory;
	
	@JsonIgnore
	@OneToMany(mappedBy = "petProduct")
	private Set<Photo> photos = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "petProduct")
	private Set<CartItem> cartItem = new HashSet<>();
	
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
