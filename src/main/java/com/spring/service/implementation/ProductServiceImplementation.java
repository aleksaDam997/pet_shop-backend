<<<<<<< HEAD
package com.spring.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditProductDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Product;
import com.spring.entity.ProductCategory;
import com.spring.repository.ProductCategoryRepository;
import com.spring.repository.ProductRepository;
import com.spring.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public Product saveProduct(AddEditProductDto productDto) {
		
		Product product = new Product();
		
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setDetails(productDto.getDetails());
		product.setVendorPrice(productDto.getVendorPrice());
		product.setRetailPrice(productDto.getRetailPrice());
		product.setDiscount(productDto.getDiscount());
		product.setQuantity(productDto.getQuantity());
		product.setStatus(productDto.getStatus());
		
		ProductCategory productCategory = this.productCategoryRepository.findById(productDto.getProductCategoryId()).get();
		product.setProductCategory(productCategory);
		
		return this.productRepository.save(product);
	}

	@Override
	public Product editProductData(Long productId, AddEditProductDto productDto) {
		
		Product product = this.productRepository.findById(productId).get();
		
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setDetails(productDto.getDetails());
		product.setVendorPrice(productDto.getVendorPrice());
		product.setRetailPrice(productDto.getRetailPrice());
		product.setDiscount(productDto.getDiscount());
		product.setQuantity(productDto.getQuantity());
		product.setStatus(productDto.getStatus());
		
		ProductCategory productCategory = this.productCategoryRepository.findById(productDto.getProductCategoryId()).get();
		product.setProductCategory(productCategory);
		
		return this.productRepository.save(product);
	}

	@Override
	public Product getProductById(Long productId) {
		
		return this.productRepository.findById(productId).get();
	}

	@Override
	public Set<Product> getPetProducts() {
		
		return (Set<Product>) this.productRepository.findAll();
	}

	@Override
	public Set<Product> searchProductsByWord(SearchDto search) {
		
		return this.productRepository.searchPetsByWord(null);
	}

	@Override
	public Set<Product> searchProductsByPetCategoryId(Long petCategoryId) {
		
		return this.productRepository.searchPetsByPetCategoryId(petCategoryId);
	}

	@Override
	public Set<Product> searchProductsByAnimalId(Long animalId) {
		
		return this.productRepository.searchPetsByAnimalId(animalId);
	}

	@Override
	public Set<Product> searchProductsByProductCategoryId(Long productCategoryId) {
		
		return this.productRepository.searchPetsByProductCategoryId(productCategoryId);
	}


}
=======
package com.spring.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditProductDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Product;
import com.spring.entity.ProductCategory;
import com.spring.repository.ProductCategoryRepository;
import com.spring.repository.ProductRepository;
import com.spring.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public Product saveProduct(AddEditProductDto productDto) {
		
		Product product = new Product();
		
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setDetails(productDto.getDetails());
		product.setVendorPrice(productDto.getVendorPrice());
		product.setRetailPrice(productDto.getRetailPrice());
		product.setDiscount(productDto.getDiscount());
		product.setQuantity(productDto.getQuantity());
		product.setStatus(productDto.getStatus());
		
		ProductCategory productCategory = this.productCategoryRepository.findById(productDto.getProductCategoryId()).get();
		product.setProductCategory(productCategory);
		
		return this.productRepository.save(product);
	}

	@Override
	public Product editProductData(Long productId, AddEditProductDto productDto) {
		
		Product product = this.productRepository.findById(productId).get();
		
		product.setCode(productDto.getCode());
		product.setName(productDto.getName());
		product.setDetails(productDto.getDetails());
		product.setVendorPrice(productDto.getVendorPrice());
		product.setRetailPrice(productDto.getRetailPrice());
		product.setDiscount(productDto.getDiscount());
		product.setQuantity(productDto.getQuantity());
		product.setStatus(productDto.getStatus());
		
		ProductCategory productCategory = this.productCategoryRepository.findById(productDto.getProductCategoryId()).get();
		product.setProductCategory(productCategory);
		
		return this.productRepository.save(product);
	}

	@Override
	public Product getProductById(Long productId) {
		
		return this.productRepository.findById(productId).get();
	}

	@Override
	public Set<Product> getPetProducts() {
		
		return (Set<Product>) this.productRepository.findAll();
	}

	@Override
	public Set<Product> searchProductsByWord(SearchDto search) {
		
		return this.productRepository.searchPetsByWord(null);
	}

	@Override
	public Set<Product> searchProductsByPetCategoryId(Long petCategoryId) {
		
		return this.productRepository.searchPetsByPetCategoryId(petCategoryId);
	}

	@Override
	public Set<Product> searchProductsByAnimalId(Long animalId) {
		
		return this.productRepository.searchPetsByAnimalId(animalId);
	}

	@Override
	public Set<Product> searchProductsByProductCategoryId(Long productCategoryId) {
		
		return this.productRepository.searchPetsByProductCategoryId(productCategoryId);
	}


}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
