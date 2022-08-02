package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.spring.dto.OrderStatusDto;
import com.spring.dto.UserOrderDto;
import com.spring.entity.Cart;
import com.spring.entity.UserOrder;
import com.spring.service.implementation.CartServiceImplementation;
import com.spring.service.implementation.UserOrderServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
public class OrderController {

	private final UserOrderServiceImplementation userOrderService;
	private final CartServiceImplementation cartService;
	

	@PostMapping("api/user/create/order")
	public UserOrder createOrder(HttpServletRequest request) {

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
			
			Cart cart = this.cartService.getLastActiveCartByUsername(username);
						
			return this.userOrderService.createOrder(cart);
		}else {
			
			return null;
		}
	}
	
	@GetMapping("api/admin/get/order")
	public List<UserOrderDto> getOrders() {
		return this.userOrderService.getOrders();
	}
	
	@PutMapping("api/admin/change/status/order/{id}")
	public UserOrder changeOrderStatus(@RequestBody OrderStatusDto status, @PathVariable("id") Long userOrderId) {
		return this.userOrderService.changeOrderStatus(userOrderId, status.getStatus());
	}
}
