package com.spring.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Photo;
import com.spring.service.implementation.PhotoServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class PhotoController {
	
	@Autowired
	private PhotoServiceImplementation photoService;
	
	@GetMapping("api/user/get/photos/pet/{id}")
	public Set<Photo> getPhotosByPetId(@PathVariable("id") Long petId) {
		return this.photoService.getPetPhotos(petId);
	}
}
