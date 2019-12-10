package com.nationwide.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nationwide.persistence.model.Note;

/**
 * This repository adds CRUD functionality capabilities to the database
 * 
 * @see https://docs.spring.io/spring-data/jpa/docs/1.8.x/reference/html/#repositories.query-methods.query-creation
 */
public interface NoteRepository extends JpaRepository<Note, Long>{

	public List<Note> findByAccountUsername(String username);
}
