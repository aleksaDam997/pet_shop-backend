package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.petProduct.petProductId = ?2")
	public CartItem getCartItemByCartAndProductId(Long cartId, Long productId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.pet.petId = ?2")
	public CartItem getCartItemByCartAndPetd(Long cartId, Long petId);
}
