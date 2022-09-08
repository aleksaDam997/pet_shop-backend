
package com.spring.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.User;

public interface PhotoService {

	public User uploadUserPhoto(MultipartFile file, Long userId);
	public Pet uploadPetPhoto(MultipartFile[] file, Long petId);
	public Set<Photo> getPetPhotos(Long petId);
	public Set<Photo> getUserPhotos(Long userId);
	public Set<Photo> getProductPhotos(Long productId);
}
