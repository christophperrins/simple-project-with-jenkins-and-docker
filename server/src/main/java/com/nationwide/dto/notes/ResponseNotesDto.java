package com.nationwide.dto.notes;

import java.util.List;

public class ResponseNotesDto {
	
	private List<NoteDto> notes;
	private String bearerToken;
	
	public List<NoteDto> getNotes() {
		return notes;
	}
	public void setNotes(List<NoteDto> notes) {
		this.notes = notes;
	}
	public String getBearerToken() {
		return bearerToken;
	}
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	
}
