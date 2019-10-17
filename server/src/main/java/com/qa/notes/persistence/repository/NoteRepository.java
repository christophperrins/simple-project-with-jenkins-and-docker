package com.qa.notes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.notes.persistence.model.Note;

/**
 * This repository adds CRUD functionality capabilities to the database
 * 
 * @see https://docs.spring.io/spring-data/jpa/docs/1.8.x/reference/html/#repositories.query-methods.query-creation
 */
public interface NoteRepository extends JpaRepository<Note, Long>{

	public Note findNoteByText(String text);
}
