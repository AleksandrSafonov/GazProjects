package com.gorgaz.gazmyas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUser {
	private String id;
	private String name;
	private boolean active;

	public ChatUser(String name) {
		this.name = name;
		this.active = true;
	}

	@Override
	public String toString() {
		return "ChatUser{" +
				"id='" + id + '\'' +
				", name ='" + name + '\'' +
				", active='" + active + '\'' +
				'}';
	}
}
