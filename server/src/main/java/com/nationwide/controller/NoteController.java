package com.nationwide.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nationwide.dto.login.ResponseLoginDto;
import com.nationwide.dto.notes.NoteDto;
import com.nationwide.dto.notes.RequestNoteDto;
import com.nationwide.dto.notes.ResponseNoteDto;
import com.nationwide.dto.notes.ResponseNotesDto;
import com.nationwide.dto.notes.ResponseTokenDto;
import com.nationwide.mapping.Mapping;
import com.nationwide.persistence.model.Note;
import com.nationwide.service.NoteService;

/**
 * Note controller. Microservice for retrieving and posting notes.
 *
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/note")
public class NoteController {
	
	@Autowired
	private NoteService service;

	@Autowired
	private LoginInterface loginService;
	
	@Autowired
	private Mapping mapping;
	
	@RequestMapping(path = "/{bearerToken}", method = {RequestMethod.GET})
	public ResponseNotesDto getNotes(@PathVariable String bearerToken){
		ResponseLoginDto loginDetails = loginService.authenticateWithToken(bearerToken);
		List<Note> notes = service.getNotes(loginDetails.getUsername());
		List<NoteDto> notesDto = mapping.map(notes, NoteDto.class);
		ResponseNotesDto response = new ResponseNotesDto();
		response.setNotes(notesDto);
		response.setBearerToken(loginDetails.getBearerToken());
		return response;
	}
	
	@RequestMapping(path = "/{bearerToken}", method = {RequestMethod.POST})
	public ResponseNoteDto createNote(@RequestBody RequestNoteDto notedto, @PathVariable String bearerToken){
		ResponseLoginDto loginDetails = loginService.authenticateWithToken(bearerToken);
		Note note = mapping.map(notedto, Note.class);
		note.setAccountUsername(loginDetails.getUsername());
		Note savedNote = service.createNote(note);
		NoteDto noteDto = mapping.map(savedNote, NoteDto.class);
		ResponseNoteDto response = new ResponseNoteDto();
		response.setNote(noteDto);
		response.setBearerToken(loginDetails.getBearerToken());
		
		return response;
		
	}
	
	@RequestMapping(path = "/{bearerToken}", method = {RequestMethod.PUT})
	public ResponseNoteDto updateNote(@RequestBody NoteDto noteDto, @PathVariable String bearerToken) {
		ResponseLoginDto loginDetails = loginService.authenticateWithToken(bearerToken);
		Note note = mapping.map(noteDto, Note.class);
		Note noteChanged = service.updateNote(note, loginDetails.getUsername());
		
		NoteDto responseNote = mapping.map(noteChanged, NoteDto.class);
		
		ResponseNoteDto response = new ResponseNoteDto();
		response.setNote(responseNote);
		response.setBearerToken(loginDetails.getBearerToken());
		return response;
	}
	
	@RequestMapping(path = "/{cardId}/{bearerToken}", method = {RequestMethod.DELETE})
	public ResponseTokenDto deleteNote(@PathVariable Long cardId, @PathVariable String bearerToken) {
		ResponseLoginDto loginDetails = loginService.authenticateWithToken(bearerToken);
		service.deleteNote(cardId, loginDetails.getUsername());
		ResponseTokenDto response = new ResponseTokenDto();
		response.setBearerToken(loginDetails.getBearerToken());
		return response;
	}
}
