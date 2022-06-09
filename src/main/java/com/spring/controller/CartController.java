package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.QuantityDto;
import com.spring.entity.Cart;
import com.spring.service.implementation.CartServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class CartController {

	@Autowired
	private CartServiceImplementation cartService;
	
	@GetMapping("api/user/get/last/cart/user/{id}")
	public Cart getLastActiveCartByUserId(@PathVariable Long userId) {
		return this.cartService.getById(userId);
	}
	
	@GetMapping("api/user/get/cart/{id}")
	public Cart getCartById(@PathVariable Long cartId) {
		return this.cartService.getById(cartId);
	}
	
	@PostMapping("api/user/add/cart/user/{id}")
	public Cart createCartForUser(@PathVariable("id") Long userId) {
		return this.cartService.createNewCartForUser(userId);
	}
	
	@PutMapping("api/user/cart/add/{cartId}/product/{productId}")
	public Cart addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId, @RequestBody QuantityDto quantityDto) {
		return this.cartService.addProductToCart(cartId, productId, quantityDto.getQuantity());
	}
	
	@PutMapping("api/user/cart/add/{cartId}/pet/{petId}")
	public Cart addPetToCart(@PathVariable("cartId") Long cartId, @PathVariable("petId") Long petId, @RequestBody QuantityDto quantityDto) {
		return this.cartService.addProductToCart(cartId, petId, quantityDto.getQuantity());
	}
	
	@PutMapping("api/user/edit/cart/{cartId}/product/{productId}")
	public Cart changeProductQuantity(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId, @RequestBody QuantityDto quantityDto) {
		return this.cartService.changeProductQuantity(cartId, productId, quantityDto.getQuantity());
	}
	@PutMapping("api/user/edit/cart/{cartId}/pet/{petId}")
	public Cart changePetQuantity(@PathVariable("cartId") Long cartId, @PathVariable("petId") Long petId, @RequestBody QuantityDto quantityDto) {
		return this.cartService.changePetQuantity(cartId, petId, quantityDto.getQuantity());
	}
	
}
