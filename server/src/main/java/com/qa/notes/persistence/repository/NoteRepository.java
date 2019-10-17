package com.qa.notes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.notes.persistence.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
