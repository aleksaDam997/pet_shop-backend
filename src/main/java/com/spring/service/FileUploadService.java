package com.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.User;

public interface FileUploadService {

	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String filename);
	public User uploadUserPhoto(MultipartFile file, Long userId);
}
