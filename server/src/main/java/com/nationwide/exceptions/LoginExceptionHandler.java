package com.nationwide.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nationwide.dto.error.ErrorDto;
import com.nationwide.mapping.Mapping;

import feign.FeignException;

@RestControllerAdvice
public class LoginExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Mapping mapping;

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> handleException(BaseException exception) {
		return new ResponseEntity<ExceptionObject>(exception.getBody(), exception.getHeaders(), exception.getStatus());
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<?> handleException(FeignException exception) {
		System.out.println(exception.status());
		System.out.println(exception.contentUTF8());
		if(exception.status() == 401) {
			ExceptionObject exceptionObject = new ExceptionObject();
			exceptionObject.setError(new ErrorDto());
			exceptionObject.getError().setStatus(401);
			exceptionObject.getError().setMessage("You are not authorised");			
			return new ResponseEntity<ExceptionObject>(exceptionObject, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}
		ExceptionObject retrievedError = mapping.jsonToPojo(exception.content(), ExceptionObject.class);
		return new ResponseEntity<ExceptionObject>(retrievedError, new HttpHeaders(), HttpStatus.resolve(retrievedError.getError().getStatus()));
	}

}
