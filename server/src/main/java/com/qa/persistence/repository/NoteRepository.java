package com.qa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.persistence.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
