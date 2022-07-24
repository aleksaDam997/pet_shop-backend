package com.spring.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.AddEditPetDto;
import com.spring.dto.PetSearchDto;
import com.spring.dto.PetDto;
import com.spring.dto.SearchDto;
import com.spring.entity.Breed;
import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.repository.BreedRepository;
import com.spring.repository.PetCustomRepository;
import com.spring.repository.PetRepository;
import com.spring.repository.PhotoRepository;
import com.spring.service.PetService;

@Service
public class PetServiceImplementation implements PetService{

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
		
		return this.petRepository.save(pet);
	}

	@Override
	public Pet getPetById(Long petId) {
		return this.petRepository.findById(petId).get();
	}

	@Override
	public List<Pet> getPets() {
		return this.petRepository.findAll();
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
