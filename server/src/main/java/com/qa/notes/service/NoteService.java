package com.qa.notes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.notes.dto.NoteDto;
import com.qa.notes.persistence.model.Note;
import com.qa.notes.persistence.model.Notebook;
import com.qa.notes.persistence.repository.NoteRepository;
import com.qa.notes.persistence.repository.NotebookRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NotebookRepository notebookRepository;

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
	 * Creates a note and adds it to a notebook
	 * @param noteDto A notebook Data Transfer Object
	 * @param notebookId notebookId for searching in notebookRepository
	 * @return A NoteDTO object which can then be sent to the client
	 * @throws NotFoundException Thrown if the notebookid is not found
	 */
	public NoteDto createNote(NoteDto noteDto, Long notebookId) throws NotFoundException{
		Note note = new Note();
		note.setText(noteDto.getText());
		System.out.println(notebookId);
		Notebook notebook = notebookRepository.findById(notebookId).orElseThrow(() -> new NotFoundException());
		notebook.getNotes().add(note);
		notebookRepository.flush();
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
