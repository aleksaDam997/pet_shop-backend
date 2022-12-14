
package com.spring.service;

import java.util.Set;

import com.spring.dto.AddEditProductDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Product;

public interface ProductService {

	public Product saveProduct(AddEditProductDto productDto);
	public Product editProductData(Long productId, AddEditProductDto productDto);
	public Product getProductById(Long productId);
	public Set<Product> getPetProducts();
	
	//Custom QUERYS
	public Set<Product> searchProductsByWord(SearchDto search);
	public Set<Product> searchProductsByPetCategoryId(Long petCategoryId);
	public Set<Product> searchProductsByAnimalId(Long animalId);
	public Set<Product> searchProductsByProductCategoryId(Long productCategoryId);

}
