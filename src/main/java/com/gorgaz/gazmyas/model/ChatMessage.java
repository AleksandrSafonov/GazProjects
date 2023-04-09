package com.gorgaz.gazmyas.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessage {
	private String id;
	private LocalDateTime dateTime;
	private String text;
	private String userId;

	public ChatMessage(String id, LocalDateTime dateTime, String text, String userId) {
		this.id = id;
		this.dateTime = dateTime;
		this.text = text;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ChatMessage{" +
				"id='" + id + '\'' +
				", LocalDateTime='" + dateTime + '\'' +
				", text='" + text + '\'' +
				", userId='" + userId + '\'' +
				'}';
	}
}
