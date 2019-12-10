package com.nationwide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nationwide.exceptions.NotFoundException;
import com.nationwide.persistence.model.Note;
import com.nationwide.persistence.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public List<Note> getNotes(String username){
		List<Note> notes = noteRepository.findByAccountUsername(username);
		return notes;
	}
	
	public Note createNote(Note note){
		return noteRepository.save(note);
	}
	
	public Note updateNote(Note note, String username) {
		List<Note> notes = getNotes(username);
		for (Note foundNote: notes) {
			if(note.getId().equals(foundNote.getId())) {
				foundNote.setText(note.getText());
				noteRepository.flush();
				return foundNote;
			}
		}
		throw new NotFoundException("note not found");
	}
	
	public String deleteNote(Long id, String username) {
		List<Note> notes = getNotes(username);
		for (Note note: notes) {
			if(id.equals(note.getId())) {
				noteRepository.delete(note);
				return "Note deleted";
			}
		}
		throw new NotFoundException("note not found");
	}
}
