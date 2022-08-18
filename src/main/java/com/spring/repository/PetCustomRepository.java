package com.spring.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.PetSearchDto;
import com.spring.entity.Pet;
import com.spring.entity.Product;
import com.spring.entity.Sex;

@Repository
public class PetCustomRepository {

	@Autowired
	private EntityManager entityManager;
	
	public List<Pet> petSearch(PetSearchDto petSearchDto) {
		
		CriteriaBuilder criteriBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pet> criteriaQuery = criteriBuilder.createQuery(Pet.class);

		Root<Pet> pet = criteriaQuery.from(Pet.class);
		
		List<Predicate> searchCriteria = new ArrayList<>();
		
		if(petSearchDto.getCategoryId() != null && petSearchDto.getCategoryId() != 0) {
			Predicate byCat = criteriBuilder.equal(pet.get("breed").get("animal").get("petCategory").get("petCategoryId"), petSearchDto.getCategoryId());
			searchCriteria.add(byCat);
		}
		
		if(petSearchDto.getAnimalId() != null && petSearchDto.getAnimalId() != 0) {
			Predicate byAnimal = criteriBuilder.equal(pet.get("breed").get("animal").get("animalId"), petSearchDto.getAnimalId());
			searchCriteria.add(byAnimal);
		}
		
		if(petSearchDto.getBreedId() != null && petSearchDto.getBreedId() != 0) {
			Predicate byBreed = criteriBuilder.equal(pet.get("breed").get("breedId"), petSearchDto.getBreedId());
			searchCriteria.add(byBreed);
		}
		
		if(petSearchDto.getSex() != "" && petSearchDto.getSex() != null) {
			Sex sex = null;
			if(petSearchDto.getSex().equals("MALE")) {
				sex = Sex.MALE;
			}else if(petSearchDto.getSex().equals("FEMALE")) {
				sex = Sex.FEMALE;
			}
			
			Predicate gender = criteriBuilder.equal(pet.get("sex"), sex);
			
			searchCriteria.add(gender);
					
		}
		
		if(petSearchDto.getKeyWords() != "" && petSearchDto.getKeyWords() != null) {
			Predicate forName = criteriBuilder.like(pet.get("name"), "%" + petSearchDto.getKeyWords() + "%");
			Predicate forDescription = criteriBuilder.like(pet.get("description"), "%" + petSearchDto.getKeyWords() + "%");
			Predicate forExcerpt = criteriBuilder.like(pet.get("excerpt"), "%" + petSearchDto.getKeyWords() + "%");
			
			Predicate search = criteriBuilder.or(forName, forDescription, forExcerpt);
			searchCriteria.add(search);
		}
		
		if(petSearchDto.getMaxPrice() != 0) {
			Predicate minPrice = criteriBuilder.greaterThan(pet.get("vendorPrice"), petSearchDto.getMinPrice());
			Predicate maxPrice = criteriBuilder.lessThan(pet.get("vendorPrice"), petSearchDto.getMaxPrice());
			Predicate price = criteriBuilder.and(minPrice, maxPrice);
			searchCriteria.add(price);
		}
		
		if(petSearchDto.getSortBy().toString() != "" && petSearchDto.getSortBy() != null) {
			if(petSearchDto.getSortBy().toString() == "SORT_BY_NAME_ASC") {
				criteriaQuery.orderBy(criteriBuilder.asc(pet.get("name")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_NAME_DESC") {
				criteriaQuery.orderBy(criteriBuilder.desc(pet.get("name")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_PRICE_ASC") {
				criteriaQuery.orderBy(criteriBuilder.asc(pet.get("vendorPrice")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_PRICE_DESC") {
				criteriaQuery.orderBy(criteriBuilder.desc(pet.get("vendorPrice")));
			}
		}
		
		
		criteriaQuery.select(pet).where(criteriBuilder.and(searchCriteria.toArray(new Predicate[searchCriteria.size()])));
		
//		TypedQuery<Pet> query = entityManager.createQuery(criteriaQuery);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public List<Product> productSearch(PetSearchDto petSearchDto) {
		
		CriteriaBuilder criteriBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriBuilder.createQuery(Product.class);

		Root<Product> product = criteriaQuery.from(Product.class);
		
		List<Predicate> searchCriteria = new ArrayList<>();
		
		if(petSearchDto.getCategoryId() != null && petSearchDto.getCategoryId() != 0) {
			Predicate byCat = criteriBuilder.equal(product.get("productCategory").get("animal").get("petCategory").get("petCategoryId"), petSearchDto.getCategoryId());
			searchCriteria.add(byCat);
		}
		
		if(petSearchDto.getAnimalId() != null && petSearchDto.getAnimalId() != 0) {
			Predicate byAnimal = criteriBuilder.equal(product.get("getCategory").get("animal").get("animalId"), petSearchDto.getAnimalId());
			searchCriteria.add(byAnimal);
		}
		
		if(petSearchDto.getBreedId() != null && petSearchDto.getBreedId() != 0) {
			Predicate byProductCat = criteriBuilder.equal(product.get("getProductCategory").get("productCategoryId"), petSearchDto.getBreedId());
			searchCriteria.add(byProductCat);
		}
		
		if(petSearchDto.getKeyWords() != "" && petSearchDto.getKeyWords() != null) {
			Predicate forName = criteriBuilder.like(product.get("name"), "%" + petSearchDto.getKeyWords() + "%");
			Predicate forDetails = criteriBuilder.like(product.get("details"), "%" + petSearchDto.getKeyWords() + "%");
			
			Predicate search = criteriBuilder.or(forName, forDetails);
			searchCriteria.add(search);
		}
		
		if(petSearchDto.getMaxPrice() != 0) {
			Predicate minPrice = criteriBuilder.greaterThan(product.get("vendorPrice"), petSearchDto.getMinPrice());
			Predicate maxPrice = criteriBuilder.lessThan(product.get("vendorPrice"), petSearchDto.getMaxPrice());
			Predicate price = criteriBuilder.and(minPrice, maxPrice);
			searchCriteria.add(price);
		}
		
		if(petSearchDto.getSortBy().toString() != "" && petSearchDto.getSortBy() != null) {
			if(petSearchDto.getSortBy().toString() == "SORT_BY_NAME_ASC") {
				criteriaQuery.orderBy(criteriBuilder.asc(product.get("name")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_NAME_DESC") {
				criteriaQuery.orderBy(criteriBuilder.desc(product.get("name")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_PRICE_ASC") {
				criteriaQuery.orderBy(criteriBuilder.asc(product.get("vendorPrice")));
			}else if(petSearchDto.getSortBy().toString() == "SORT_BY_PRICE_DESC") {
				criteriaQuery.orderBy(criteriBuilder.desc(product.get("vendorPrice")));
			}
		}
		
		
		criteriaQuery.select(product).where(criteriBuilder.and(searchCriteria.toArray(new Predicate[searchCriteria.size()])));
		
//		TypedQuery<Pet> query = entityManager.createQuery(criteriaQuery);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
}
