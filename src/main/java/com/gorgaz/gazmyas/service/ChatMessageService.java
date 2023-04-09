package com.gorgaz.gazmyas.service;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.mapper.ChatMessageMapper;
import com.gorgaz.gazmyas.model.ChatMessage;
import com.gorgaz.gazmyas.repository.ChatMessageRepository;
import com.gorgaz.gazmyas.repository.IChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ChatMessageService implements IChatMessageService {

	@Value("${defaultMessageDepthDays}")
	private int defaultMessageDepthDays;

	IChatMessageRepository chatMessageRepository = new ChatMessageRepository();

	public List<ChatMessageRq> getMessages() {
		List<ChatMessage> chatMessages;
		if (defaultMessageDepthDays == 0) {
			chatMessages = chatMessageRepository.findAll();
		} else {
			chatMessages = chatMessageRepository.findAllAfterDate(LocalDateTime.now().minusDays(defaultMessageDepthDays));
		}
		return ChatMessageMapper.toRqs(chatMessages);
	}

	public ChatMessageRq getMessage(String id) {
		ChatMessage chatMessage = chatMessageRepository.findById(id);
		return ChatMessageMapper.toRq(chatMessage);
	}

	public boolean existById(String id) {
		return chatMessageRepository.existById(id);
	}

	public List<ChatMessageRq> getMessagesAfterDate(LocalDateTime startDate) {
		List<ChatMessage> chatMessages = chatMessageRepository.findAllAfterDate(startDate);
		return ChatMessageMapper.toRqs(chatMessages);
	}

	public String saveMessage(ChatMessageRq rq) {
		ChatMessage message = ChatMessageMapper.toChatMessage(rq);
		return chatMessageRepository.save(message).getId();
	}

	public void removeMessage(String id) {
		chatMessageRepository.remove(id);
	}

}
