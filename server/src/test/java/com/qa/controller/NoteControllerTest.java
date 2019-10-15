package com.qa.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.dto.NoteDto;
import com.qa.persistence.repository.NoteRepository;
import com.qa.service.NoteService;

@RunWith(MockitoJUnitRunner.class)
public class NoteControllerTest {

	@InjectMocks
	private NoteController noteController;
	
	@Mock
	private NoteService noteService;
	
	@Test
	public void getNotesTest() {
		List<NoteDto> noteDtos = new ArrayList<NoteDto>();
		noteDtos.add(new NoteDto(1L, "Text"));
		noteDtos.add(new NoteDto(3L, "More Text"));
		
		Mockito.when(noteService.getNotes()).thenReturn(noteDtos);
		
		assertEquals(HttpStatus.OK, noteController.getNotes().getStatusCode());
	}
	

	@Test
	public void createNoteTest() {
		NoteDto dto = new NoteDto(null, "Text");
		Mockito.when(noteService.createNote(dto)).thenReturn(new NoteDto(2L, "Text"));
		
		assertEquals((Long) 2L, noteController.createNote(dto).getBody().getId());
		assertEquals(HttpStatus.CREATED, noteController.createNote(dto).getStatusCode());
	}
	

	@Test
	public void updateNoteTest() {
		NoteDto dto = new NoteDto(1L, "Text");
		Mockito.when(noteService.updateNote(dto)).thenReturn(new NoteDto(1L, "Text"));
		
		assertEquals((Long) 1L, noteController.updateNote(dto).getBody().getId());
		assertEquals(HttpStatus.ACCEPTED, noteController.updateNote(dto).getStatusCode());
	}
	
	@Test
	public void deleteNoteTest() {
		Long id = 1L;
		NoteDto dto = new NoteDto(1L, "Text");
		Mockito.when(noteService.deleteNote(id)).thenReturn(dto);
		
		assertEquals((Long) 1L, noteController.deleteNote(id).getBody().getId());
		assertEquals(HttpStatus.ACCEPTED, noteController.deleteNote(id).getStatusCode());
		
	}
		
}
