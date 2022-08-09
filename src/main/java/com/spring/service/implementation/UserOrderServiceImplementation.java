package com.spring.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring.dto.CartDto;
import com.spring.dto.PetDto;
import com.spring.dto.UserOrderDto;
import com.spring.entity.Cart;
import com.spring.entity.CartItem;
import com.spring.entity.OrderStatus;
import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.User;
import com.spring.entity.UserOrder;
import com.spring.repository.CartItemRepository;
import com.spring.repository.CartRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.PhotoRepository;
import com.spring.repository.UserOrderRepository;
import com.spring.repository.UserRepository;
import com.spring.service.UserOrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserOrderServiceImplementation implements UserOrderService {

	private final UserOrderRepository userOrderRepository;
	private final CartRepository cartRepo;
	private final EmailSenderServiceImplementation emailSender;
	private final PetRepository petRep;
	private final CartItemRepository cartItemRepository;
	private final PhotoRepository photosRepo;
	private final UserRepository userRepository;
	
	
	@Override
	public UserOrder createOrder(Cart cart) {
		
		UserOrder userOrder = new UserOrder();
		userOrder.setCart(cart);
		userOrder.setStatus(OrderStatus.PENDING);
		
//		emailSender.send(cart.getUser().getEmail(), null);

		return this.userOrderRepository.save(userOrder);
	}
	
	public List<UserOrderDto> getOrders() {
		List<UserOrder> orders = this.userOrderRepository.findAll();
		return orderWithDetails(orders);
	}
	
	public UserOrder changeOrderStatus(Long userOrderId, String status) {
		UserOrder order = this.userOrderRepository.findById(userOrderId).get();
		
		if(status.equals("PENDING")) {
			 order.setStatus(OrderStatus.PENDING);
		}else if(status.equals("REJECTED")) {
			order.setStatus(OrderStatus.REJECTED);
		}else if(status.equals("ACCEPTED")) {
			order.setStatus(OrderStatus.ACCEPTED);
		}else if(status.equals("SHIPPED")) {
			order.setStatus(OrderStatus.SHIPPED);
		}
		

		
		return this.userOrderRepository.save(order);
	}

	public List<UserOrderDto> getOrdersByStatus(String status) {
		
		OrderStatus orderStatus = null;
		
		if(status.equals("REJECTED")) {
			orderStatus = OrderStatus.REJECTED;
		}else if(status.equals("ACCEPTED")) {
			orderStatus = OrderStatus.ACCEPTED;
		}else if(status.equals("PENDING")) {
			orderStatus = OrderStatus.PENDING;
		}else if(status.equals("SHIPPED")) {
			orderStatus = OrderStatus.SHIPPED;
		}
		
		List<UserOrder> orders = this.userOrderRepository.getOrdersByStatus(orderStatus);
		return orderWithDetails(orders);
	}
	
	public List<UserOrderDto> getOrdersByUsername(String username) {
		
		User user = this.userRepository.findByUsername(username).get();
		
		if(user == null) {
			return null;
		}
		
		List<UserOrder> orders = this.userOrderRepository.getOrdersByUserId(user.getUserId());
		return orderWithDetails(orders);
	}
	
	public List<UserOrderDto> orderWithDetails(List<UserOrder> orders) {
		List<UserOrderDto> ordersToReturn = new ArrayList<>();
		
		
		for(UserOrder order : orders) {
			
			UserOrderDto orderToPush = new UserOrderDto();
			orderToPush.setUserOrderId(order.getUserOrderId());
			orderToPush.setCreatedAt(order.getCreatedAt());
			orderToPush.setStatus(order.getStatus());
			
			Cart cart = this.cartRepo.getCartByOrderId(order.getUserOrderId());
			
			CartDto cd = new CartDto();
			
			cd.setCartId(cart.getCartId());
			cd.setCreatedAt(cart.getCreatedAt());
			cd.setUser(cart.getUser());
			cd.setUserOrder(cart.getUserOrder());
			
			Set<Pet> pet = this.petRep.findBycartId(cd.getCartId());
			Set<PetDto> petCart = new HashSet<>();
			
			for(Pet p : pet) {
				
				PetDto petDto = new PetDto();
				
				petDto.setPetId(p.getPetId());
				petDto.setName(p.getName());
				petDto.setVendorPrice(p.getVendorPrice());
				petDto.setRetailPrice(p.getRetailPrice());
				petDto.setDiscount(p.getDiscount());
				petDto.setAge(p.getAge());
				
				CartItem ci = this.cartItemRepository.getCartItemByCartAndPetd(cd.getCartId(), p.getPetId());
				Set<Photo> photos = this.photosRepo.findByPetPetId(p.getPetId());
				
				petDto.setQuantity(ci.getQuantity());
				petDto.setPhotos(photos);
				
				petCart.add(petDto);
			}
			
			cd.setPets(petCart);
			
			orderToPush.setCart(cd);
			
			ordersToReturn.add(orderToPush);
		}
		
		return ordersToReturn;
	}
}
