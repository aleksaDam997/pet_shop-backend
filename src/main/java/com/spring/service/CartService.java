package com.spring.service;

import java.util.List;

import com.spring.entity.Cart;
import com.spring.entity.CartItem;
import com.spring.entity.Pet;
import com.spring.entity.Product;
import com.spring.entity.User;

public interface CartService {

	public Cart getLastActiveCartByUserId(Long userId);
	public Cart createNewCartForUser(Long userId);
	public Cart addProductToCart(Long cartId, Long productId, int quantity);
	public Cart addPetToCart(Long cartId, Long petId, int quantity);
	public Cart changeProductQuantity(Long cartId, Long productId, int newQuantity);
	public Cart changePetQuantity(Long cartId, Long petId, int newQuantity);
	public Cart getById(Long cartId);
	public Cart getLastActiveCartByUsername(String username);
	public CartItem addPetToCart(Pet pet, Cart cart, int quantity);
	public List<CartItem> getCartItemByCartId(Long cartId);
}
