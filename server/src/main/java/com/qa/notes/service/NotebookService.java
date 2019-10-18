package com.qa.notes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.notes.persistence.model.Note;
import com.qa.notes.persistence.model.Notebook;
import com.qa.notes.persistence.repository.NotebookRepository;

@Service
public class NotebookService {

	@Autowired
	private NotebookRepository notebookRepository;
	
	public List<Notebook> getNotebooks() {
		return notebookRepository.findAll();
	}
	
	public List<Note> getNotesForNotebook(Long id) throws NotFoundException {
		Notebook notebook = notebookRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return notebook.getNotes();
	}
	
	public Notebook addNotebook(Notebook notebook) {
		return notebookRepository.saveAndFlush(notebook);
	}
	
	public Notebook updateNotebook(Notebook notebook) throws NotFoundException {
		Notebook dbNotebook = notebookRepository.findById(notebook.getId()).orElseThrow(() -> new NotFoundException());
		dbNotebook.setTitle(notebook.getTitle());
		dbNotebook.setColour(notebook.getColour());
		notebookRepository.flush();
		return dbNotebook;
	}
	
	public Notebook deleteNotebook(Long id) throws NotFoundException {
		Notebook notebook = notebookRepository.findById(id).orElseThrow(() -> new NotFoundException());
		notebookRepository.delete(notebook);
		return notebook;
	}
	
}
