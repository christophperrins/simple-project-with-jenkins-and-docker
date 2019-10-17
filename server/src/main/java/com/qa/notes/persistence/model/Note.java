package com.qa.notes.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * An entity is something which will be managed by the JPA (Java Persistence API)
 *
 */
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String text;
	
	public Note() {
		super();
	}
	
	public Note(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	// Should be a factory pattern 
	public static Note createNote() {
		return new Note();
	}
	
}
