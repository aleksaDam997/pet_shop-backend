<<<<<<< HEAD
package com.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.User;

public interface FileUploadService {

	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String filename);
	public User uploadUserPhoto(MultipartFile file, Long userId);
}
=======
package com.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.User;

public interface FileUploadService {

	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String filename);
	public User uploadUserPhoto(MultipartFile file, Long userId);
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
