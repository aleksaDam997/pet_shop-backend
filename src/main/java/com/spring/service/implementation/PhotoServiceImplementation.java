package com.spring.service.implementation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.config.ResourceConfig;
import com.spring.entity.Pet;
import com.spring.entity.Photo;
import com.spring.entity.User;
import com.spring.repository.PetRepository;
import com.spring.repository.PhotoRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.UserRepository;
import com.spring.service.PhotoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PhotoServiceImplementation implements PhotoService{
	
	private UserRepository userRepository;
	private PetRepository petRep;
	private ProductRepository produtRep;
	private PhotoRepository photoRepository;

	@Override
	public User uploadUserPhoto(MultipartFile file, Long userId) {
		
		User user = this.userRepository.findById(userId).get();
		
		String filename = this.generatePhotoName(StringUtils.cleanPath(file.getOriginalFilename()));
		
		String finalPath = ResourceConfig.uploadDirectory + "/user/";
		
		try {
			byte[] fileToStore = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(finalPath + filename));
			fos.write(fileToStore);
			fos.flush();
			fos.close();
			
			BufferedImage bimg = ImageIO.read(new File(finalPath + filename));
			BufferedImage smallImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = smallImage.createGraphics();
			g.drawImage(bimg, 0, 0, 128, 128, null);
			g.dispose();
			ImageIO.write(smallImage, "jpg", new File(finalPath + "small/" + filename));
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		user.setPhotoPath(filename);
		
		return this.userRepository.save(user);
	}

	@Override
	public Pet uploadPetPhoto(MultipartFile file, Long petId) {

		Pet pet = this.petRep.findById(petId).get();
		
		String filename = this.generatePhotoName(StringUtils.cleanPath(file.getOriginalFilename()));
		
		String finalPath = ResourceConfig.uploadDirectory + "pet/";
		
		try {
			byte[] fileToStore = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(finalPath + filename));
			fos.write(fileToStore);
			fos.flush();
			fos.close();
			
			BufferedImage bimg = ImageIO.read(new File(finalPath + filename));
			BufferedImage smallImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = smallImage.createGraphics();
			g.drawImage(bimg, 0, 0, 128, 128, null);
			g.dispose();
			ImageIO.write(smallImage, "jpg", new File(finalPath + "small/" + filename));
			
			BufferedImage mediumImage = new BufferedImage(456, 382, BufferedImage.TYPE_INT_RGB);
			g = mediumImage.createGraphics();
			g.drawImage(bimg, 0, 0, 456, 382, null);
			g.dispose();
			ImageIO.write(mediumImage, "jpg", new File(finalPath + "medium/" + filename));
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		Photo photo = new Photo();
		photo.setPath(filename);
		photo.setPet(pet);
		
		this.photoRepository.save(photo);		
		
		return this.petRep.findById(petId).get();
	}
	
	private String generatePhotoName(String photoRealName) {
		
		String newName = "";
		Random r = new Random();
		
		for(int i = 0; i < 11; i++) {

			newName = newName + r.nextInt(10);
		}
		
		newName = newName + photoRealName;
		
		return newName;
	}
	
	public Set<Photo> getPhotosForUserById(Long userId) {
		return this.photoRepository.findByPetPetId(userId);
	}

	@Override
	public Set<Photo> getPetPhotos(Long petId) {
		
		return this.photoRepository.findByPetPetId(petId);
	}

	@Override
	public Set<Photo> getUserPhotos(Long userId) {
		return null;
//		return this.photoRepository.findByUserId(userId);
	}

	@Override
	public Set<Photo> getProductPhotos(Long productId) {
		return null;
//		return this.getProductPhotos(productId);
	}

}
