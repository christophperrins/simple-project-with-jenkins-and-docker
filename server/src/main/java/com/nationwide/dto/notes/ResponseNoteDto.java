package com.nationwide.dto.notes;

public class ResponseNoteDto {

	private NoteDto note;
	private String bearerToken;
	
	public NoteDto getNote() {
		return note;
	}
	public void setNote(NoteDto note) {
		this.note = note;
	}
	public String getBearerToken() {
		return bearerToken;
	}
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}

}
