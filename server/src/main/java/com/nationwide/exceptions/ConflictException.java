 package com.nationwide.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException{

	private static final long serialVersionUID = 2629639850474266996L;

	public ConflictException(String message) {
		super(message, new HttpHeaders(), HttpStatus.CONFLICT);
	}

	
}
