package com.gorgaz.gazmyas.repository;

import com.gorgaz.gazmyas.model.ChatUser;

import java.util.List;

public interface IChatUserRepository {

	List<ChatUser> findAll();

	List<ChatUser> findAllActive();

	ChatUser findById(String id);

	ChatUser save(ChatUser message);

	void clear();

	boolean existById(String id);

	void remove(String id);
}