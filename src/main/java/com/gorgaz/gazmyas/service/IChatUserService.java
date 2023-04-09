package com.gorgaz.gazmyas.service;

import com.gorgaz.gazmyas.gererated.model.ChatUserRq;

import java.time.OffsetDateTime;
import java.util.List;

public interface IChatUserService {

	List<ChatUserRq> getUsers(boolean onlyActive);

	ChatUserRq getUser(String id);

	boolean existById(String id);

	String saveUser(ChatUserRq rq);

	void removeUser(String id);

}
