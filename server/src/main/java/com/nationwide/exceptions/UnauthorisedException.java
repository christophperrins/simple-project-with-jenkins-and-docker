package com.nationwide.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.nationwide.dto.error.ErrorDto;

public class UnauthorisedException extends BaseException{
	
	private static final long serialVersionUID = 2763297522709229651L;

	public UnauthorisedException(String message) {
		super(message, new HttpHeaders(), HttpStatus.UNAUTHORIZED);	
	}
		
}
