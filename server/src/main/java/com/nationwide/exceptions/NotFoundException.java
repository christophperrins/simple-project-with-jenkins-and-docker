package com.nationwide.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException{

	private static final long serialVersionUID = 365964715003520490L;

	public NotFoundException(String message) {
		super(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
}
