package com.qa.notes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qa.notes.dto.NoteDto;
import com.qa.notes.dto.NotebookDto;
import com.qa.notes.persistence.model.Notebook;
import com.qa.notes.service.NotebookService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/notebook")
public class NotebookController {

	@Autowired
	private NotebookService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<NotebookDto> getNotebooks() {
		return service.getNotebooks().stream().map(notebook -> new NotebookDto(notebook)).collect(Collectors.toList());
	}
	
	@GetMapping(path = "/{notebookId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<NoteDto> getNotes(@PathVariable Long notebookId) throws NotFoundException{
		return service.getNotesForNotebook(notebookId).stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public NotebookDto postNotebook(@RequestBody NotebookDto dto) {
		dto.setId(null);
		return new NotebookDto(service.addNotebook(new Notebook(dto)));
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public NotebookDto putNotebook(@RequestBody NotebookDto dto) throws NotFoundException {
		return new NotebookDto(service.updateNotebook(new Notebook(dto)));
	}

	@DeleteMapping(path = "/{notebookId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public NotebookDto deleteNotebook(@PathVariable Long notebookId) throws NotFoundException{
		return new NotebookDto(service.deleteNotebook(notebookId));
	}
}
