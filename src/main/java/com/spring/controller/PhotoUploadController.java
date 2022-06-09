package com.spring.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.User;
import com.spring.service.implementation.PhotoServiceImplementation;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class PhotoUploadController {
	
	@Autowired
	private PhotoServiceImplementation fileUploadService;
	
	@PostMapping("api/user/{id}/add/photo")
	public User uploadUserPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long userId) {
		return this.fileUploadService.uploadUserPhoto(file, userId);
	}
	
	@PostMapping("api/admin/pet/{id}/add/photo")
	public Pet uploadPetPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long petId) {
		return this.fileUploadService.uploadPetPhoto(file, petId);
	}
	
}
