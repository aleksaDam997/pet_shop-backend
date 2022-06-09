package com.spring.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.entity.Cart;
import com.spring.entity.CartItem;
import com.spring.entity.Product;
import com.spring.entity.User;
import com.spring.repository.CartItemRepository;
import com.spring.repository.CartRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.UserRepository;
import com.spring.service.CartService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
@NoArgsConstructor
public class CartServiceImplementation implements CartService{
	
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private CartItemRepository cartItemRepository;
	private ProductRepository petProductRep;
	private PetRepository petRep;
	
	@Override
	public Cart getLastActiveCartByUserId(Long userId) {

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(userId);
		
		if(carts == null) {
			return null;
		}
		
		Cart cart = null;
		
		for(int i = 0; i < carts.size(); i++) {
			Cart c = carts.get(i);
			
			if(c.getUserOrder() != null) {
				continue;
			}else {
				cart = c;
				break;
			}
		}
		
		return cart;
	}
	@Override
	public Cart createNewCartForUser(Long userId) {

		Cart cart = new Cart();
		
		if(!this.userRepository.findById(userId).isPresent()) {
			return null;
		}
		
		User user = this.userRepository.findById(userId).get();
		
		cart.setUser(user);
		
		return this.cartRepository.save(cart);
	}
	
	@Override
	public Cart addProductToCart(Long cartId, Long productid, int quantity) {

		Cart cart = this.cartRepository.findById(cartId).get();
		Product product = this.petProductRep.findById(productid).get();
	
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setPetProduct(product);
		cartItem.setQuantity(quantity);
		
		cart.getCartItem().add(cartItem);
		
		return this.cartRepository.save(cart);
	}
	
	@Override
	public Cart changeProductQuantity(Long cartId, Long productId, int newQuantity) {
		
		CartItem cartItem = this.cartItemRepository.getCartItemByCartAndProductId(cartId, productId);
	
		if(cartItem == null) {
			return null;
		}
		
		cartItem.setQuantity(newQuantity);
		
		this.cartItemRepository.save(cartItem);
		
		return this.cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart changePetQuantity(Long cartId, Long petId, int newQuantity) {
		
		CartItem cartItem = this.cartItemRepository.getCartItemByCartAndPetd(cartId, petId);
	
		if(cartItem == null) {
			return null;
		}
		
		cartItem.setQuantity(newQuantity);
		
		this.cartItemRepository.save(cartItem);
		
		return this.cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart getById(Long cartId) {
		
		Cart cart = this.cartRepository.findById(cartId).get();
		
		if(!this.cartItemRepository.findById(cartId).isPresent()) {
			return null;
		}
		
		return cart;
	}
	@Override
	public Cart addPetToCart(Long cartId, Long petId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}


}
