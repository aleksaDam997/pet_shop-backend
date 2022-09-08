<<<<<<< HEAD
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.petProduct.petProductId = ?2")
	public CartItem getCartItemByCartAndProductId(Long cartId, Long productId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.pet.petId = ?2")
	public CartItem getCartItemByCartAndPetd(Long cartId, Long petId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1")
	public List<CartItem> getCartItemByCartId(Long cartId);
}
=======
package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.petProduct.petProductId = ?2")
	public CartItem getCartItemByCartAndProductId(Long cartId, Long productId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.pet.petId = ?2")
	public CartItem getCartItemByCartAndPetd(Long cartId, Long petId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1")
	public List<CartItem> getCartItemByCartId(Long cartId);
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
