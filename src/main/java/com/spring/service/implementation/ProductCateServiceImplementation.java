package com.spring.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.ProductCategory;
import com.spring.repository.ProductCategoryRepository;
import com.spring.service.ProductCategoryService;

@Service
public class ProductCateServiceImplementation implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryRepository productCateRepository;

	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		
		return this.productCateRepository.save(productCategory);
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		
		return this.productCateRepository.findAll();
	}

	@Override
	public ProductCategory getProductCatById(Long productCategoryId) {
		
		return this.productCateRepository.findById(productCategoryId).get();
	}

}
