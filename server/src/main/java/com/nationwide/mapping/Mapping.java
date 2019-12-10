package com.nationwide.mapping;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nationwide.exceptions.InternalException;

public class Mapping {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;

	public <T, S> S map(T source, Class<S> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public <T, S> List<S> map(List<T> source, Class<S> destinationType) {
		return source.stream().map(sourceItem -> map(sourceItem, destinationType)).collect(Collectors.toList());
	}

	public <T, S> Set<S> map(Set<T> source, Class<S> destinationType) {
		return source.stream().map(sourceItem -> map(sourceItem, destinationType)).collect(Collectors.toSet());
	}
	
	public <S> S jsonToPojo(byte[] source, Class<S> destinationClass) {
		try {
			return objectMapper.readValue(source, destinationClass);
		} catch (JsonParseException e) {
			throw new InternalException("String is not accepted as json syntax");
		} catch (JsonMappingException e) {
			throw new InternalException("json string not convertible to specified object");
		} catch (IOException e) {
			throw new InternalException("IO error");
		}
	}
}
