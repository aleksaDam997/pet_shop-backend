package com.spring.service.implementation;

import org.springframework.stereotype.Service;

import com.spring.entity.Cart;
import com.spring.entity.OrderStatus;
import com.spring.entity.UserOrder;
import com.spring.repository.CartRepository;
import com.spring.repository.UserOrderRepository;
import com.spring.service.UserOrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class UserOrderServiceImplementation implements UserOrderService {

	private final UserOrderRepository userOrderRepository;
	private final CartRepository cartRepo;
	
	@Override
	public UserOrder createOrder(Cart cart) {
		
		UserOrder userOrder = new UserOrder();
		userOrder.setCart(cart);
		userOrder.setStatus(OrderStatus.PENDING);

		return this.userOrderRepository.save(userOrder);
	}

}
