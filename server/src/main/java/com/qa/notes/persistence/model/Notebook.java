package com.qa.notes.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Set<Note> notes = new HashSet<Note>();

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

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
	
}
