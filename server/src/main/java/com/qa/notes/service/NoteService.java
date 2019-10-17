package com.qa.notes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.notes.dto.NoteDto;
import com.qa.notes.persistence.model.Note;
import com.qa.notes.persistence.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	/**
	 * Get all notes in the database
	 * @return
	 * List of all NoteDto objects
	 */
	public List<NoteDto> getNotes(){
		List<Note> notes = noteRepository.findAll();
		List<NoteDto> notesDto = new ArrayList<NoteDto>();
		notes.forEach(note -> notesDto.add(new NoteDto(note)));
		return notesDto;
	}
	
	/**
	 * Used to save a Note into the database
	 * @param noteDto 
	 * a Note a DTO. Whether id is present makes no difference
	 * @return 
	 * a Note DTO, with a given id from the database.
	 */
	public NoteDto createNote(NoteDto noteDto){
		noteDto.setId(null);
		Note note = new Note();
		note.setText(noteDto.getText());
		
		return new NoteDto(noteRepository.saveAndFlush(note));
	}
	
	/**
	 * Updates a note text.
	 * @param noteDto 
	 * A note DTO. ID must be present 
	 * @return
	 * Returns the updates object
	 * @throws NotFoundException 
	 * If id is not found in database it will throw the exception
	 */
	public NoteDto updateNote(NoteDto noteDto) throws NotFoundException{
		Note note = noteRepository.findById(noteDto.getId()).orElseThrow(() -> new NotFoundException());
		note.setText(noteDto.getText());
		noteRepository.flush();
		return new NoteDto(note);
	}
	
	/**
	 * Deletes a note text.
	 * @param id
	 * id of the note which must be deleted.
	 * @return
	 * returns an empty object.
	 * @throws NotFoundException 
	 * If id is not found in database it will throw the exception
	 */
	public NoteDto deleteNote(Long id) throws NotFoundException{
		Note note = noteRepository.findById(id).orElseThrow(() -> new NotFoundException());
		NoteDto noteDto = new NoteDto(note);
		noteRepository.deleteById(id);
		return noteDto;
	}
}
