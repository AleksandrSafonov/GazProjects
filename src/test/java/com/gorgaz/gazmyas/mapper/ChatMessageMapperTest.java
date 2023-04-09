package com.gorgaz.gazmyas.mapper;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.model.ChatMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ChatMessageMapperTest {

	private static final String MESSAGE_ID = "931ec463-ab0d-451a-8a35-442cc71976f4";
	private static final String USER_ID = "00000000-0000-1111-1111-111111111111";

	@Test
	void toRq() {
		LocalDateTime dateTime = LocalDateTime.of(2023, 4, 3, 8, 0, 0);
		var chatMessage = new ChatMessage(MESSAGE_ID, dateTime, "Текст 1", USER_ID);
		ChatMessageRq chatMessageRq = ChatMessageMapper.toRq(chatMessage);
		assertThat(chatMessageRq).isNotNull();
		assertThat(chatMessageRq.getDateTime()).isNotNull();
		assertThat(chatMessageRq.getDateTime()).isEqualTo(dateTime.toString());
	}

	@Test
	void toChatMessage() {
		LocalDateTime dateTime = LocalDateTime.of(2023, 4, 3, 8, 0, 0);
		var chatMessageRq = new ChatMessageRq(UUID.fromString(MESSAGE_ID), UUID.fromString(USER_ID), dateTime).text("Текст 1");
		ChatMessage chatMessage = ChatMessageMapper.toChatMessage(chatMessageRq);
		assertThat(chatMessage).isNotNull();
		assertThat(chatMessage.getDateTime()).isNotNull();
		assertThat(chatMessage.getDateTime()).isEqualTo(dateTime.toString());
	}

}
