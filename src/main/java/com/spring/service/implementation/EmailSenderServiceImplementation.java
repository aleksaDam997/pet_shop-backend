<<<<<<< HEAD
package com.spring.service.implementation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spring.service.EmailSenderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailSenderServiceImplementation implements EmailSenderService {

	private final JavaMailSender javaMailSender;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailSenderServiceImplementation.class);
	
	@Override
	@Async
	public void send(String to, String email) {

		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			
			helper.setTo(to);
			helper.setText(email, true);
			helper.setFrom("aleksa.car1997@gmail.com");
			helper.setSubject("Confirm your email to complete registration..");
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			LOGGER.error("Failed to send message", e);
			throw new IllegalStateException("Failed to send message");
		}

	}

}
=======
package com.spring.service.implementation;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spring.service.EmailSenderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmailSenderServiceImplementation implements EmailSenderService {

	private final JavaMailSender javaMailSender;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailSenderServiceImplementation.class);
	
	@Override
	@Async
	public void send(String to, String email) {

		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			
			helper.setTo(to);
			helper.setText(email, true);
			helper.setFrom("aleksa.car1997@gmail.com");
			helper.setSubject("Confirm your email to complete registration..");
			javaMailSender.send(mimeMessage);
			
		} catch (Exception e) {
			LOGGER.error("Failed to send message", e);
			throw new IllegalStateException("Failed to send message");
		}

	}

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
