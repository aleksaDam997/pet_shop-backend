package com.spring.service.implementation;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@NoArgsConstructor
@Slf4j
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
	
	
	@Override
	public Cart getLastActiveCartByUserId(Long userId) {

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(userId);
		
		if(carts == null) {
			return null;
		}
		
		
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
		
		User user = this.userRepository.findByEmail(username).get();
		
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
		
		log.info("username is: " + username);
		
		User user = this.userRepository.findByEmail(username).get();
		
		if(user == null) {
			return null;
		}

		List<Cart> carts = this.cartRepository.getLastActiveCartByUserId(user.getUserId());
		
		
		if(carts == null || carts.size() == 0) {
			return createNewCartForUser(username);
		}
		
		log.info("Cart info: " + carts.get(0).getCartId() + " this is id...");

		
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
			petDto.setQuantity(p.getQuantity());
			petDto.setSex(p.getSex());
			petDto.setStatus(p.getStatus());
			
			CartItem ci = this.cartItemRepository.getCartItemByCartAndPetd(cd.getCartId(), p.getPetId());
			
			petDto.setCartQuantity(ci.getQuantity());
			
			petCart.add(petDto);
		}
		
		cd.setPets(petCart);
		
		
		
		
		
		return cd;
	}
	
	
	
	@Override
	public CartDto createNewCartForUser(String username) {

		Cart cart = new Cart();
		
		if(!this.userRepository.findByUsername(username).isPresent()) {
			return null;
		}
		
		User user = this.userRepository.findByUsername(username).get();
		
		cart.setUser(user);
		
		Cart savedCart = this.cartRepository.save(cart);
		
		CartDto cartDto = new CartDto();
		cartDto.setCartId(savedCart.getCartId());
		cartDto.setUser(savedCart.getUser());
		cartDto.setCreatedAt(savedCart.getCreatedAt());
		cartDto.setUserOrder(savedCart.getUserOrder());
		
		return cartDto;
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
	public Cart addPetToCart(Long petId, String username, int quantity) {
		
		Cart cart = this.getLastActiveCartByUsername(username);
		Pet pet = this.petRep.findById(petId).get();
		
		CartItem ci = this.cartItemRepository.getCartItemByCartAndPetd(cart.getCartId(), pet.getPetId());
		
		if(ci != null) {
			return this.changePetQuantity(cart.getCartId(), pet.getPetId(), quantity + ci.getQuantity());
		}
				
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		
		cartItem.setPet(pet);
		cartItem.setQuantity(quantity);
				
		this.cartItemRepository.save(cartItem);
		
		return this.cartRepository.findById(cart.getCartId()).get();
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
	public List<CartItem> getCartItemByCartId(Long cartId) {
		
		return this.cartItemRepository.getCartItemByCartId(cartId);
	}



}
