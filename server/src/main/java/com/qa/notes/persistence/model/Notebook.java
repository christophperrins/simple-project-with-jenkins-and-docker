package com.qa.notes.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.qa.notes.dto.NotebookDto;

@Entity
public class Notebook {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String colour;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Note> notes = new ArrayList<Note>();

	public Notebook() {
		
	}
	
	public Notebook(NotebookDto dto) {
		this.id = dto.getId();
		this.title = dto.getTitle();
		this.colour = dto.getColour();
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
	
}
