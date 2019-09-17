package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.dto.NoteDto;
import com.qa.persistence.model.Note;
import com.qa.persistence.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	public List<NoteDto> getNotes(){
		List<Note> notes = noteRepository.findAll();
		List<NoteDto> notesDto = new ArrayList<NoteDto>();
		notes.forEach(note -> notesDto.add(new NoteDto(note)));
		return notesDto;
	}
	
	public NoteDto createNote(NoteDto noteDto){
		noteDto.setId(null);
		Note note = Note.createNote();
		note.setText(noteDto.getText());
		
		return new NoteDto(noteRepository.saveAndFlush(note));
	}
	
	public NoteDto updateNote(NoteDto noteDto){
		Note note = noteRepository.getOne(noteDto.getId());
		note.setText(noteDto.getText());
		noteRepository.flush();
		return new NoteDto(note);
	}
	
	public NoteDto deleteNote(Long id){
		Note note = noteRepository.getOne(id);
		NoteDto noteDto = new NoteDto(note);
		noteRepository.deleteById(id);
		return noteDto;
	}
}
