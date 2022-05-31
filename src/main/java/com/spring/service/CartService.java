package com.spring.service;

import java.util.List;

import com.spring.entity.Cart;
import com.spring.entity.Pet;
import com.spring.entity.Product;
import com.spring.entity.User;

public interface CartService {

	public Cart getLastActiveCartByUserId(Long userId);
	public Cart createNewCartForUser(Long userId);
	public Cart addArticleToCart(Long cartId, Long articleId, int quantity);
	public Cart changeQuantity(Long cartId, Long articleId, int newQuantity);
	public Cart getById(Long cartId);
}
