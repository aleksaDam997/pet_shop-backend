package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.dto.QuantityDto;
import com.spring.entity.Cart;
import com.spring.entity.CartItem;
import com.spring.entity.Pet;
import com.spring.service.implementation.CartServiceImplementation;
import com.spring.service.implementation.PetServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class CartController {
	
	 Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartServiceImplementation cartService;
	
	@Autowired
	private PetServiceImplementation petService;
	
	
	@GetMapping("api/user/get/last/active/cart/")
	public Cart getLastActiveCartByUserId(HttpServletRequest request) {
		
		String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(token);
				
				username = decodedJWT.getSubject();
			}catch(Exception e) {
				
			}
			
			return this.cartService.getLastActiveCartByUsername(username);
		}else {
			
			return null;
		}

	}
	
	@PostMapping("api/user/add/cart/pet/{id}")
	public Cart addPetToCart(HttpServletRequest req, @PathVariable("id") Long petId, @RequestBody QuantityDto quantity) {
		
		String authorizationHeader = req.getHeader("Authorization");
		String username = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			try {
				String token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(token);
				
				username = decodedJWT.getSubject();
			}catch(Exception e) {
			}
			
			Cart cart = this.cartService.getLastActiveCartByUsername(username);
			Pet pet = this.petService.getPetById(petId);
			
			this.cartService.addPetToCart(pet, cart, quantity.getQuantity());
			
			return this.cartService.getById(cart.getCartId());
			
		}else {
			return new Cart();
		}
		
	}
	
	
	@GetMapping("api/user/get/cartItems/cart/{id}")
	public List<CartItem> getCartItemByCartId(@PathVariable("id") Long cartId) {
		return this.cartService.getCartItemByCartId(cartId);
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
