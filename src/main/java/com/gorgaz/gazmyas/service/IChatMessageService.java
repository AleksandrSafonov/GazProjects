package com.gorgaz.gazmyas.service;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.model.ChatMessage;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public interface IChatMessageService {
	List<ChatMessageRq> getMessages();

	ChatMessageRq getMessage(String id);

	boolean existById(String id);

	List<ChatMessageRq> getMessagesAfterDate(LocalDateTime startDate);

	String saveMessage(ChatMessageRq rq);

	void removeMessage(String id);

	void removeAllChatMessage();

}
