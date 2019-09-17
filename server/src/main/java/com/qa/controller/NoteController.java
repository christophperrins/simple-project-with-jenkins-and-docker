package com.qa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dto.NoteDto;
import com.qa.service.NoteService;

@RestController
@CrossOrigin("*")
public class NoteController {
	
	@Autowired
	private NoteService service;

	@RequestMapping(path = "note/", method = {RequestMethod.GET})
	public ResponseEntity<List<NoteDto>> getNotes(){
		return new ResponseEntity<List<NoteDto>>(service.getNotes(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "note/", method = {RequestMethod.POST})
	public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto note){
		return new ResponseEntity<NoteDto>(service.createNote(note), HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "note/", method = {RequestMethod.PUT})
	public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto note){
		return new ResponseEntity<NoteDto>(service.updateNote(note), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path = "note/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<NoteDto> deleteNote(@PathVariable Long id){
		return new ResponseEntity<NoteDto>(service.deleteNote(id), HttpStatus.ACCEPTED);
	}
}
