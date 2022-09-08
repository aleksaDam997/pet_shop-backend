<<<<<<< HEAD
package com.spring.service;

import java.util.List;

import com.spring.dto.CartDto;
import com.spring.entity.Cart;
import com.spring.entity.CartItem;

public interface CartService {

	public Cart getLastActiveCartByUserId(Long userId);
	public Cart addProductToCart(Long cartId, Long productId, int quantity);
	public Cart changeProductQuantity(Long cartId, Long productId, int newQuantity);
	public Cart changePetQuantity(Long cartId, Long petId, int newQuantity);
	public Cart getById(Long cartId);
	public Cart addPetToCart(Long petId, String username, int quantity);
	public List<CartItem> getCartItemByCartId(Long cartId);
	
	//Trebaju sig.
	public CartDto getLastActiveCartDtoByUsername(String username);
	public Cart getLastActiveCartByUsername(String username);
	CartDto createNewCartForUser(String username);
}
=======
package com.spring.service;

import java.util.List;

import com.spring.dto.CartDto;
import com.spring.entity.Cart;
import com.spring.entity.CartItem;

public interface CartService {

	public Cart getLastActiveCartByUserId(Long userId);
	public Cart addProductToCart(Long cartId, Long productId, int quantity);
	public Cart changeProductQuantity(Long cartId, Long productId, int newQuantity);
	public Cart changePetQuantity(Long cartId, Long petId, int newQuantity);
	public Cart getById(Long cartId);
	public Cart addPetToCart(Long petId, String username, int quantity);
	public List<CartItem> getCartItemByCartId(Long cartId);
	
	//Trebaju sig.
	public CartDto getLastActiveCartDtoByUsername(String username);
	public Cart getLastActiveCartByUsername(String username);
	CartDto createNewCartForUser(String username);
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
