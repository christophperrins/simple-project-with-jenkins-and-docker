package com.qa.notes.junit.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.notes.controller.NoteController;
import com.qa.notes.dto.NoteDto;
import com.qa.notes.persistence.repository.NoteRepository;
import com.qa.notes.service.NoteService;

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
		assertEquals(2, noteController.getNotes().size());
	}
	

	@Test
	public void createNoteTest() {
		NoteDto dto = new NoteDto(null, "Text");
		Mockito.when(noteService.createNote(dto)).thenReturn(new NoteDto(2L, "Text"));
		
		assertEquals((Long) 2L, noteController.createNote(dto).getId());
	}
	

	@Test
	public void updateNoteTest() throws NotFoundException {
		NoteDto dto = new NoteDto(1L, "Text");
		Mockito.when(noteService.updateNote(dto)).thenReturn(new NoteDto(1L, "Text"));
		
		assertEquals((Long) 1L, noteController.updateNote(dto).getId());
	}
	
	@Test
	public void deleteNoteTest() throws NotFoundException {
		Long id = 1L;
		NoteDto dto = new NoteDto(1L, "Text");
		Mockito.when(noteService.deleteNote(id)).thenReturn(dto);
		
		assertEquals((Long) 1L, noteController.deleteNote(id).getId());		
	}
		
}
