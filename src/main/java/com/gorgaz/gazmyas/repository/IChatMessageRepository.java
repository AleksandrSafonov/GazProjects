package com.gorgaz.gazmyas.repository;

import com.gorgaz.gazmyas.model.ChatMessage;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface IChatMessageRepository {

	List<ChatMessage> findAll();

	ChatMessage findById(String id);

	List<ChatMessage> findAllAfterDate(LocalDateTime startDate);

	ChatMessage save(ChatMessage message);

	void clear();

	void remove(String id);

	boolean existById(String id);
}