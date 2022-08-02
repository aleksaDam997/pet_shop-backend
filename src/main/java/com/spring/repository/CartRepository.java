package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("SELECT c FROM Cart c WHERE c.user.userId = ?1")
	public List<Cart> getLastActiveCartByUserId(Long userId);
	
	@Query("SELECT c FROM Cart c WHERE c.userOrder.userOrderId = ?1")
	public Cart getCartByOrderId(Long userOrderId);
}
