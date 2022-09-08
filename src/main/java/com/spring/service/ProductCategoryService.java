
package com.spring.service;

import java.util.List;

import com.spring.dto.AddEditAnimalDto;
import com.spring.entity.ProductCategory;

public interface ProductCategoryService {

	public ProductCategory saveProductCategory(ProductCategory productCategory);
	public List<ProductCategory> getAllProductCategories();
	public ProductCategory getProductCatById(Long productCategoryId);
}
