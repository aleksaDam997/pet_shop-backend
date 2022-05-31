package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
