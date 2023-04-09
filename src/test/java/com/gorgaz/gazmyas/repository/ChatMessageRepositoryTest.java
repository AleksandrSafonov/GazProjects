package com.gorgaz.gazmyas.repository;

import com.gorgaz.gazmyas.model.ChatMessage;
import com.gorgaz.gazmyas.repository.ChatMessageRepository;
import com.gorgaz.gazmyas.repository.IChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class ChatMessageRepositoryTest {

	IChatMessageRepository chatMessageRepository = new ChatMessageRepository();

	@BeforeEach
	void init() {
		chatMessageRepository.clear();
	}

	@Test
	void findAllAfterDate() {
		LocalDateTime startDate = LocalDateTime.of(2023, 4, 3, 8, 0, 0);

		var chatMessage = new ChatMessage("001",
				LocalDateTime.of(2023, 4, 3, 8, 0, 1),
				"Текст 1",
				"000-001");
		chatMessageRepository.save(chatMessage);
		assertEquals(1, chatMessageRepository.findAllAfterDate(startDate).size());

		chatMessage = new ChatMessage("002",
				LocalDateTime.of(2023, 4, 3, 7, 0, 0),
				"Текст 1",
				"000-001");
		chatMessageRepository.save(chatMessage);
		assertEquals(1, chatMessageRepository.findAllAfterDate(startDate).size());

		assertEquals(2, chatMessageRepository.findAll().size());
	}

	@Test
	void save() {
		var chatMessage = new ChatMessage(UUID.randomUUID().toString(),
				LocalDateTime.of(2023, 4, 3, 8, 0, 1),
				"Текст 1",
				"000-001");
		ChatMessage resChatMessage = chatMessageRepository.save(chatMessage);
		assertEquals(chatMessage.getDateTime(), resChatMessage.getDateTime());
		assertEquals(chatMessage.getUserId(), resChatMessage.getUserId());
		assertEquals(chatMessage.getText(), resChatMessage.getText());
		assertNotNull(resChatMessage.getId());
	}

}
