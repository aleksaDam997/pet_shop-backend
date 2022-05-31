package com.spring.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartItemId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "cartId")
	private Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id", referencedColumnName = "petId")
	private Pet pet;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_product_id", referencedColumnName = "petProductId")
	private Product petProduct;
	
	private int quantity;
}
