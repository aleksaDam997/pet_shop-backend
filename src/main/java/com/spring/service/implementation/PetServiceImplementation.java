<<<<<<< HEAD
package com.spring.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditPetDto;
import com.spring.dto.PetDto;
import com.spring.dto.PetSearchDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Breed;
import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.Status;
import com.spring.repository.BreedRepository;
import com.spring.repository.PetCustomRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.PhotoRepository;
import com.spring.service.PetService;

@Service
public class PetServiceImplementation implements PetService{
	Logger logger = LoggerFactory.getLogger(PetServiceImplementation.class);

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private BreedRepository breedRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private PetCustomRepository petCustomRepository;
	
	public List<PetDto> searchForPets(PetSearchDto petSearchDto){
		
		List<Pet> pets = this.petCustomRepository.petSearch(petSearchDto);
		
		List<PetDto> petsWithPhotos = new ArrayList<>();
		
		for(Pet p: pets) {
			Set<Photo> petPhotos = this.photoRepository.findByPetPetId(p.getPetId());
			
			PetDto finalPet = new PetDto();
			finalPet.setPetId(p.getPetId());
			finalPet.setName(p.getName());
			finalPet.setDescription(p.getDescription());
			finalPet.setExcerpt(p.getExcerpt());
			finalPet.setAge(p.getAge());
			finalPet.setColor(p.getColor());
			finalPet.setEyesColor(p.getEyesColor());
			finalPet.setSex(p.getSex());
			finalPet.setQuantity(p.getQuantity());
			finalPet.setVendorPrice(p.getVendorPrice());
			finalPet.setRetailPrice(p.getRetailPrice());
			finalPet.setDiscount(p.getDiscount());
			finalPet.setStatus(p.getStatus());
			finalPet.setPhotos(petPhotos);
			
			
			petsWithPhotos.add(finalPet);
		}
		
		return petsWithPhotos;
	}

	
	@Override
	public Pet savePet(AddEditPetDto addEditPetDto) {

		Pet pet = new Pet();
		
		pet.setName(addEditPetDto.getName());
		pet.setDescription(addEditPetDto.getDescription());
		pet.setExcerpt(addEditPetDto.getExcerpt());
		pet.setAge(addEditPetDto.getAge());
		pet.setColor(addEditPetDto.getColor());
		pet.setEyesColor(addEditPetDto.getEyesColor());
		pet.setSex(addEditPetDto.getSex());
		pet.setQuantity(addEditPetDto.getQuantity());
		pet.setVendorPrice(addEditPetDto.getVendorPrice());
		pet.setRetailPrice(addEditPetDto.getRetailPrice());
		pet.setDiscount(addEditPetDto.getDiscount());
		pet.setStatus(Status.ON_SALE);
		
		Breed breed = this.breedRepository.findById(addEditPetDto.getBreedId()).get();
		
		pet.setBreed(breed);
		
		return this.petRepository.save(pet);
	}

	@Override
	public Pet editPetData(Long petId, AddEditPetDto editPetDto) {
		
		Pet pet = this.petRepository.findById(petId).get();
		
		pet.setName(editPetDto.getName());
		pet.setDescription(editPetDto.getDescription());
		pet.setExcerpt(editPetDto.getExcerpt());
		pet.setAge(editPetDto.getAge());
		pet.setColor(editPetDto.getColor());
		pet.setEyesColor(editPetDto.getEyesColor());
		pet.setSex(editPetDto.getSex());
		pet.setQuantity(editPetDto.getQuantity());
		pet.setVendorPrice(editPetDto.getVendorPrice());
		pet.setRetailPrice(editPetDto.getRetailPrice());
		pet.setDiscount(editPetDto.getDiscount());
		pet.setStatus(editPetDto.getStatus());
		
		Breed breed = this.breedRepository.findById(editPetDto.getBreedId()).get();
		
		pet.setBreed(breed);
		
		return this.petRepository.save(pet);
	}

	@Override
	public Pet getPetById(Long petId) {
		return this.petRepository.findById(petId).get();
	}


		public List<PetDto> getPets() {
		List<Pet> pets = this.petRepository.findAll();
		List<PetDto> petDtos = new ArrayList<>();
		
		for(Pet p : pets) {
			PetDto petToReturn = new PetDto();
			
			petToReturn.setPetId(p.getPetId());
			petToReturn.setName(p.getName());
			petToReturn.setDescription(p.getDescription());
			petToReturn.setExcerpt(p.getExcerpt());
			petToReturn.setAge(p.getAge());
			petToReturn.setColor(p.getColor());
			petToReturn.setEyesColor(p.getEyesColor());
			petToReturn.setSex(p.getSex());
			petToReturn.setQuantity(p.getQuantity());
			petToReturn.setVendorPrice(p.getVendorPrice());
			petToReturn.setRetailPrice(p.getRetailPrice());
			petToReturn.setDiscount(p.getDiscount());
			petToReturn.setStatus(p.getStatus());
			
			petToReturn.setBreed(p.getBreed());
//			petToReturn.setCartItem(pet.getCartItem());
			
			Set<Photo> photos = this.photoRepository.findByPetPetId(p.getPetId());
			
			petToReturn.setPhotos(photos);
			
			petDtos.add(petToReturn);
		}
	
		return petDtos;

	}
	
	public PetDto getPetWithPhotos(Long petId) { 
		PetDto petToReturn = new PetDto();
		Pet pet = this.petRepository.findById(petId).get();
		
		petToReturn.setName(pet.getName());
		petToReturn.setDescription(pet.getDescription());
		petToReturn.setExcerpt(pet.getExcerpt());
		petToReturn.setAge(pet.getAge());
		petToReturn.setColor(pet.getColor());
		petToReturn.setEyesColor(pet.getEyesColor());
		petToReturn.setSex(pet.getSex());
		petToReturn.setQuantity(pet.getQuantity());
		petToReturn.setVendorPrice(pet.getVendorPrice());
		petToReturn.setRetailPrice(pet.getRetailPrice());
		petToReturn.setDiscount(pet.getDiscount());
		petToReturn.setStatus(pet.getStatus());
		
		petToReturn.setBreed(pet.getBreed());
//		petToReturn.setCartItem(pet.getCartItem());
		
		Set<Photo> photos = this.photoRepository.findByPetPetId(petId);
		
		petToReturn.setPhotos(photos);
		
		return petToReturn;
				
	}

	@Override
	public Set<Pet> searchPetsByWord(SearchDto search) {
		
		return this.petRepository.searchPetsByWord(search.getWord());
	}

	@Override
	public Set<Pet> searchPetsByPetCategoryId(Long petCategoryId) {
		
		return this.petRepository.searchPetsByCategoryId(petCategoryId);
	}

	@Override
	public Set<Pet> searchPetsByBreedId(Long breedId) {
		
		return this.petRepository.searchPetsByBreedId(breedId);
	}

	@Override
	public Set<Pet> searchPetsByAnimalId(Long animalId) {
		
		return this.petRepository.searchPetsByAnimalId(animalId);
	}
	
	


}
=======
package com.spring.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditPetDto;
import com.spring.dto.PetDto;
import com.spring.dto.PetSearchDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Breed;
import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.Status;
import com.spring.repository.BreedRepository;
import com.spring.repository.PetCustomRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.PhotoRepository;
import com.spring.service.PetService;

@Service
public class PetServiceImplementation implements PetService{
	Logger logger = LoggerFactory.getLogger(PetServiceImplementation.class);

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private BreedRepository breedRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private PetCustomRepository petCustomRepository;
	
	public List<PetDto> searchForPets(PetSearchDto petSearchDto){
		
		List<Pet> pets = this.petCustomRepository.petSearch(petSearchDto);
		
		List<PetDto> petsWithPhotos = new ArrayList<>();
		
		for(Pet p: pets) {
			Set<Photo> petPhotos = this.photoRepository.findByPetPetId(p.getPetId());
			
			PetDto finalPet = new PetDto();
			finalPet.setPetId(p.getPetId());
			finalPet.setName(p.getName());
			finalPet.setDescription(p.getDescription());
			finalPet.setExcerpt(p.getExcerpt());
			finalPet.setAge(p.getAge());
			finalPet.setColor(p.getColor());
			finalPet.setEyesColor(p.getEyesColor());
			finalPet.setSex(p.getSex());
			finalPet.setQuantity(p.getQuantity());
			finalPet.setVendorPrice(p.getVendorPrice());
			finalPet.setRetailPrice(p.getRetailPrice());
			finalPet.setDiscount(p.getDiscount());
			finalPet.setStatus(p.getStatus());
			finalPet.setPhotos(petPhotos);
			
			
			petsWithPhotos.add(finalPet);
		}
		
		return petsWithPhotos;
	}

	
	@Override
	public Pet savePet(AddEditPetDto addEditPetDto) {

		Pet pet = new Pet();
		
		pet.setName(addEditPetDto.getName());
		pet.setDescription(addEditPetDto.getDescription());
		pet.setExcerpt(addEditPetDto.getExcerpt());
		pet.setAge(addEditPetDto.getAge());
		pet.setColor(addEditPetDto.getColor());
		pet.setEyesColor(addEditPetDto.getEyesColor());
		pet.setSex(addEditPetDto.getSex());
		pet.setQuantity(addEditPetDto.getQuantity());
		pet.setVendorPrice(addEditPetDto.getVendorPrice());
		pet.setRetailPrice(addEditPetDto.getRetailPrice());
		pet.setDiscount(addEditPetDto.getDiscount());
		pet.setStatus(Status.ON_SALE);
		
		Breed breed = this.breedRepository.findById(addEditPetDto.getBreedId()).get();
		
		pet.setBreed(breed);
		
		return this.petRepository.save(pet);
	}

	@Override
	public Pet editPetData(Long petId, AddEditPetDto editPetDto) {
		
		Pet pet = this.petRepository.findById(petId).get();
		
		pet.setName(editPetDto.getName());
		pet.setDescription(editPetDto.getDescription());
		pet.setExcerpt(editPetDto.getExcerpt());
		pet.setAge(editPetDto.getAge());
		pet.setColor(editPetDto.getColor());
		pet.setEyesColor(editPetDto.getEyesColor());
		pet.setSex(editPetDto.getSex());
		pet.setQuantity(editPetDto.getQuantity());
		pet.setVendorPrice(editPetDto.getVendorPrice());
		pet.setRetailPrice(editPetDto.getRetailPrice());
		pet.setDiscount(editPetDto.getDiscount());
		pet.setStatus(editPetDto.getStatus());
		
		Breed breed = this.breedRepository.findById(editPetDto.getBreedId()).get();
		
		pet.setBreed(breed);
		
		return this.petRepository.save(pet);
	}

	@Override
	public Pet getPetById(Long petId) {
		return this.petRepository.findById(petId).get();
	}


		public List<PetDto> getPets() {
		List<Pet> pets = this.petRepository.findAll();
		List<PetDto> petDtos = new ArrayList<>();
		
		for(Pet p : pets) {
			PetDto petToReturn = new PetDto();
			
			petToReturn.setPetId(p.getPetId());
			petToReturn.setName(p.getName());
			petToReturn.setDescription(p.getDescription());
			petToReturn.setExcerpt(p.getExcerpt());
			petToReturn.setAge(p.getAge());
			petToReturn.setColor(p.getColor());
			petToReturn.setEyesColor(p.getEyesColor());
			petToReturn.setSex(p.getSex());
			petToReturn.setQuantity(p.getQuantity());
			petToReturn.setVendorPrice(p.getVendorPrice());
			petToReturn.setRetailPrice(p.getRetailPrice());
			petToReturn.setDiscount(p.getDiscount());
			petToReturn.setStatus(p.getStatus());
			
			petToReturn.setBreed(p.getBreed());
//			petToReturn.setCartItem(pet.getCartItem());
			
			Set<Photo> photos = this.photoRepository.findByPetPetId(p.getPetId());
			
			petToReturn.setPhotos(photos);
			
			petDtos.add(petToReturn);
		}
	
		return petDtos;

	}
	
	public PetDto getPetWithPhotos(Long petId) { 
		PetDto petToReturn = new PetDto();
		Pet pet = this.petRepository.findById(petId).get();
		
		petToReturn.setName(pet.getName());
		petToReturn.setDescription(pet.getDescription());
		petToReturn.setExcerpt(pet.getExcerpt());
		petToReturn.setAge(pet.getAge());
		petToReturn.setColor(pet.getColor());
		petToReturn.setEyesColor(pet.getEyesColor());
		petToReturn.setSex(pet.getSex());
		petToReturn.setQuantity(pet.getQuantity());
		petToReturn.setVendorPrice(pet.getVendorPrice());
		petToReturn.setRetailPrice(pet.getRetailPrice());
		petToReturn.setDiscount(pet.getDiscount());
		petToReturn.setStatus(pet.getStatus());
		
		petToReturn.setBreed(pet.getBreed());
//		petToReturn.setCartItem(pet.getCartItem());
		
		Set<Photo> photos = this.photoRepository.findByPetPetId(petId);
		
		petToReturn.setPhotos(photos);
		
		return petToReturn;
				
	}
	
	public Pet changePetStatus(String status, Long petId) {
		
		Status stat = null;
		
		if(status.equals("ON_SALE") || status.equals("SOLD") || status.equals("SHIPPING")) {
			stat = Status.valueOf(status);
		}
		
		Pet pet = this.petRepository.findById(petId).get();
		pet.setStatus(stat);
		
		return this.petRepository.save(pet);
	}

	@Override
	public Set<Pet> searchPetsByWord(SearchDto search) {
		
		return this.petRepository.searchPetsByWord(search.getWord());
	}

	@Override
	public Set<Pet> searchPetsByPetCategoryId(Long petCategoryId) {
		
		return this.petRepository.searchPetsByCategoryId(petCategoryId);
	}

	@Override
	public Set<Pet> searchPetsByBreedId(Long breedId) {
		
		return this.petRepository.searchPetsByBreedId(breedId);
	}

	@Override
	public Set<Pet> searchPetsByAnimalId(Long animalId) {
		
		return this.petRepository.searchPetsByAnimalId(animalId);
	}
	
	


}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
