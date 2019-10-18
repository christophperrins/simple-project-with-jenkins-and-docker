package com.qa.notes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.notes.persistence.model.Notebook;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {

}
