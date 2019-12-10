package com.nationwide.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import com.nationwide.dto.error.ErrorDto;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5223152763889976807L;

	private ExceptionObject body;
	
	private MultiValueMap<String, String> headers;

	private HttpStatus status;

	public BaseException(String message, MultiValueMap<String, String> headers, HttpStatus status) {
		this.status = status;
		this.headers = headers;
		this.body = getBody(message);
	}
	
	public ExceptionObject getBody(String message) {
		ExceptionObject exceptionObject = new ExceptionObject();
		ErrorDto error = new ErrorDto();
		error.setMessage(message);
		error.setStatus(this.status.value());
		exceptionObject.setError(error);
		System.out.println(exceptionObject.getError().getMessage().toString());
		return exceptionObject;
	}

	public ExceptionObject getBody() {
		return body;
	}

	public void setBody(ExceptionObject body) {
		this.body = body;
	}

	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(MultiValueMap<String, String> headers) {
		this.headers = headers;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	
	
}
