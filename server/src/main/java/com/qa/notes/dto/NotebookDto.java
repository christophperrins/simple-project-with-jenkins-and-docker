package com.qa.notes.dto;

import com.qa.notes.persistence.model.Notebook;

public class NotebookDto {

	private Long id;
	private String title;
	private String colour;
	
	public NotebookDto() {
		
	}
	
	public NotebookDto(Notebook notebook) {
		this.id = notebook.getId();
		this.title = notebook.getTitle();
		this.colour = notebook.getColour();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
}
