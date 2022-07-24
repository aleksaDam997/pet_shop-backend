package com.spring.service.implementation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.CartDto;
import com.spring.dto.PetDto;
import com.spring.entity.Cart;
import com.spring.entity.CartItem;
import com.spring.entity.Pet;
import com.spring.entity.Product;
import com.spring.entity.User;
import com.spring.repository.CartItemRepository;
import com.spring.repository.CartRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.UserRepository;
import com.spring.service.CartService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
@NoArgsConstructor
public class CartServiceImplementation implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private ProductRepository petProductRep;
	@Autowired
	private PetRepository petRep;
	
	Logger logger = LoggerFactory.getLogger(CartServiceImplementation.class);
	
	@Override
	public Cart getLastActiveCartByUserId(Long userId) {

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(userId);
		
		if(carts == null) {
			return null;
		}
		
		logger.debug(carts.toString());
		
		Cart cart = null;
		
		for(int i = 0; i < carts.size(); i++) {
			Cart c = carts.get(i);
			
			if(c.getUserOrder() != null) {
				continue;
			}else {
				cart = c;
				break;
			}
		}
		
		return cart;
	}
	
	@Override
	public Cart getLastActiveCartByUsername(String username) {
		
		User user = this.userRepository.findByUsername(username).get();
		
		if(user == null) {
			return null;
		}

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(user.getUserId());
		
		if(carts == null) {
			return null;
		}
		
		Cart cart = null;
		Date date = new Date(1, 1, 1980);
		
		for(int i = 0; i < carts.size(); i++) {
			Cart c = carts.get(i);
			
			if(c.getUserOrder() != null) {
				continue;
			}else {

				if(c.getCreatedAt().after(date)) {
					cart = c;
				}
			}
		}
		
		if(cart == null) {
			cart = new Cart();
			cart.setUser(user);
		}
		
		return cart;
		
	}
	
	@Override
	public CartDto getLastActiveCartDtoByUsername(String username) {
		
		User user = this.userRepository.findByUsername(username).get();
		
		if(user == null) {
			return null;
		}

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(user.getUserId());
		
		if(carts == null) {
			return null;
		}
		
		Cart cart = null;
		Date date = new Date(1, 1, 1980);
		
		for(int i = 0; i < carts.size(); i++) {
			Cart c = carts.get(i);
			
			if(c.getUserOrder() != null) {
				continue;
			}else {

				if(c.getCreatedAt().after(date)) {
					cart = c;
				}
			}
		}
				
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
			
			petDto.setQuantity(ci.getQuantity());
			
			petCart.add(petDto);
		}
		
		cd.setPets(petCart);
		
		
		
		
		
		return cd;
	}
	
	
	
	@Override
	public Cart createNewCartForUser(Long userId) {

		Cart cart = new Cart();
		
		if(!this.userRepository.findById(userId).isPresent()) {
			return null;
		}
		
		User user = this.userRepository.findById(userId).get();
		
		cart.setUser(user);
		
		return this.cartRepository.save(cart);
	}
	
	@Override
	public Cart addProductToCart(Long cartId, Long productid, int quantity) {

		Cart cart = this.cartRepository.findById(cartId).get();
		Product product = this.petProductRep.findById(productid).get();
	
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setPetProduct(product);
		cartItem.setQuantity(quantity);
		
		cart.getCartItem().add(cartItem);
		
		return this.cartRepository.save(cart);
	}
	
	@Override
	public CartItem addPetToCart(Long petId, String username, int quantity) {
		
		Cart cart = this.getLastActiveCartByUsername(username);
		
		CartItem ci = this.cartItemRepository.getCartItemByCartAndPetd(cart.getCartId(), petId);
		
		if(ci != null) {
			ci.setQuantity(ci.getQuantity() + quantity);
			
			return this.cartItemRepository.save(ci);
		}
				
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		
		Pet pet = this.petRep.findById(petId).get();
		
		logger.info(pet.getPetId() +"");
		
		cartItem.setPet(pet);
		cartItem.setQuantity(quantity);
		
		return this.cartItemRepository.save(cartItem);
	}
	
	public Cart deletePetCartItem(Long cartId, Long petId) {
		CartItem cartItem = this.cartItemRepository.getCartItemByCartAndPetd(cartId, petId);
		
		
		this.cartItemRepository.delete(cartItem);
		
		return this.cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart changeProductQuantity(Long cartId, Long productId, int newQuantity) {
		
		CartItem cartItem = this.cartItemRepository.getCartItemByCartAndProductId(cartId, productId);
	
		if(cartItem == null) {
			return null;
		}
		
		cartItem.setQuantity(newQuantity);
		
		this.cartItemRepository.save(cartItem);
		
		return this.cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart changePetQuantity(Long cartId, Long petId, int newQuantity) {
		
		CartItem cartItem = this.cartItemRepository.getCartItemByCartAndPetd(cartId, petId);
	
		if(cartItem == null) {
			return null;
		}
		
		cartItem.setQuantity(newQuantity);
		
		this.cartItemRepository.save(cartItem);
		
		return this.cartRepository.findById(cartId).get();
	}
	
	@Override
	public Cart getById(Long cartId) {
		
		Cart cart = this.cartRepository.findById(cartId).get();
		
		if(!this.cartItemRepository.findById(cartId).isPresent()) {
			return null;
		}
		
		return cart;
	}
	@Override
	public Cart addPetToCart(Long cartId, Long petId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItem> getCartItemByCartId(Long cartId) {
		
		return this.cartItemRepository.getCartItemByCartId(cartId);
	}





}
