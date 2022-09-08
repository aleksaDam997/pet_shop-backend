package com.spring.service;

import com.spring.entity.Cart;
import com.spring.entity.UserOrder;

public interface UserOrderService {
	
	public UserOrder createOrder(Cart cart);

}
