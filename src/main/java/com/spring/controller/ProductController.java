package com.spring.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.AddEditProductDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Pet;
import com.spring.entity.Product;
import com.spring.service.implementation.ProductServiceImplementation;

@RestController
public class ProductController {

	@Autowired
	private ProductServiceImplementation productService;
	
	
	@PostMapping("api/admin/add/product")
	public Product addProduct(@RequestBody AddEditProductDto productDto) {
		return this.productService.saveProduct(productDto);
	}
	
	@GetMapping("api/user/get/product")
	public Set<Product> getAllProducts() {
		return this.productService.getPetProducts();
	}
	
	@GetMapping("api/user/get/product/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping("api/user/get/product/byPetCategory/{id}")
	public Set<Product> petsByPetCategory(@PathVariable("id") Long id) {
		return this.productService.searchProductsByPetCategoryId(id);
	}
	
	@GetMapping("api/user/get/product/byAnimal/{id}")
	public Set<Product> petsByAnimalId(@PathVariable("id") Long id) {
		return this.productService.searchProductsByAnimalId(id);
	}
	
	@GetMapping("api/user/get/product/byProductCategory/{id}")
	public Set<Product> petsByproductCategoryId(@PathVariable("id") Long id) {
		return this.productService.searchProductsByProductCategoryId(id);
	}
	
	@PostMapping("api/user/get/product/search")
	public Set<Product> productssByWord(@RequestBody SearchDto search) {
		return this.productService.searchProductsByWord(search);
	}
	
}
