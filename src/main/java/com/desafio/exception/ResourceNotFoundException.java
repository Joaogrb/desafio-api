package com.desafio.exception;

public class ResourceNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(Long id) {
		this(String.format("Resource %d not found.", id));
	}

}
