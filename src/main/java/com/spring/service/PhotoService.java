package com.spring.service;

import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Pet;
import com.spring.entity.User;

public interface PhotoService {

	public User uploadUserPhoto(MultipartFile file, Long userId);
	public Pet uploadPetPhoto(MultipartFile file, Long petId);
}
