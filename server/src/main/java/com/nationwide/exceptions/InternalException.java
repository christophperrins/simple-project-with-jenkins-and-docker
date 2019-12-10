package com.nationwide.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class InternalException extends BaseException{

	private static final long serialVersionUID = 7637583443742563983L;

	public InternalException(String message) {
		super(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
