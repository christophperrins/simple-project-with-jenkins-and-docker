package com.nationwide.persistence.model;

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
	@GeneratedValue
	private Long id;
	private String text;
	private String accountUsername;
	
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
	public String getAccountUsername() {
		return accountUsername;
	}
	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}	
}
