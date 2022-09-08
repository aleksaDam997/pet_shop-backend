package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.ProductCategory;
import com.spring.service.implementation.ProductCateServiceImplementation;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCateServiceImplementation productCategoryService;
	
	@PostMapping("api/admin/add/productCategory")
	public ProductCategory saveproductCategory(@RequestBody ProductCategory productCategory) {
		return this.productCategoryService.saveProductCategory(productCategory);
	}
	
	@GetMapping("api/user/get/productCategory")
	public List<ProductCategory> getProductCategories() {
		return this.productCategoryService.getAllProductCategories();
	}
	
	@GetMapping("api/user/get/productCategory/{id}")
	public ProductCategory getProductCategoryById(@PathVariable("id") Long productCategoryId) {
		return this.productCategoryService.getProductCatById(productCategoryId);
	}
}
