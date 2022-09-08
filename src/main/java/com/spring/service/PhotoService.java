<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
