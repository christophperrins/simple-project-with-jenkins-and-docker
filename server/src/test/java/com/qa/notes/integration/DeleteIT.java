package com.qa.notes.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;

import com.qa.notes.dto.NoteDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DeleteIT {


	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void checkpost1() throws RestClientException, URISyntaxException {
		NoteDto postedRecordDto = null;
		List<NoteDto> noteDtos = restTemplate.exchange("http://localhost:"+port+"/note/", HttpMethod.GET, null, new ParameterizedTypeReference<List<NoteDto>>(){}).getBody();
		int length = noteDtos.size();
		for(NoteDto dto: noteDtos) {
			if (dto.getText().equals("Testing1")) {
				postedRecordDto = dto;
			}
		}
		
		Long deleteId = postedRecordDto.getId();
		restTemplate.delete(new URI("http://localhost:"+port+"/note/"+deleteId));
		List<NoteDto> DtosMinusOne = restTemplate.exchange("http://localhost:"+port+"/note/", HttpMethod.GET, null, new ParameterizedTypeReference<List<NoteDto>>(){}).getBody();

		assertEquals(length-1, DtosMinusOne.size());
	}
	
}