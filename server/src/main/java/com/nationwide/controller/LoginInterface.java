package com.nationwide.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nationwide.dto.login.ResponseLoginDto;

@FeignClient("login")
public interface LoginInterface {

	@RequestMapping(value = "/api/login/{bearerToken}", method = { RequestMethod.GET })
	public ResponseLoginDto authenticateWithToken(@PathVariable("bearerToken") String bearerToken);

}
