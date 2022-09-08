package com.spring.service.implementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.entity.ConfirmationToken;
import com.spring.repository.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImplementation {

	private final ConfirmationTokenRepository confirmaionTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		this.confirmaionTokenRepository.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token) {
		return this.confirmaionTokenRepository.findByToken(token);
	}
	
	public void setConfirmedAt(String token) {
		this.getToken(token).get().setConfirmedAt(LocalDateTime.now());
	}
	
}
