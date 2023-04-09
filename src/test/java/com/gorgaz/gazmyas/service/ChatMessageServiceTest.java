package com.gorgaz.gazmyas.service;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.model.ChatMessage;
import com.gorgaz.gazmyas.repository.IChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceTest
{
	@InjectMocks
	IChatMessageService service = new ChatMessageService();

	@Mock
	IChatMessageRepository chatMessageRepository;


	private static final String MESSAGE_ID = "931ec463-ab0d-451a-8a35-442cc71976f4";
	private static final String USER_ID = "00000000-0000-1111-1111-111111111111";
	private static final String TEXT_1 = "Тест1";

	ChatMessageRq chatMessageRq;

	ChatMessage chatMessage;

	@BeforeEach
	void init() {
		chatMessageRq = new ChatMessageRq(
				UUID.fromString(MESSAGE_ID),
				UUID.fromString(USER_ID),
				LocalDateTime.now())
				.text(TEXT_1);

		chatMessage = new ChatMessage(MESSAGE_ID, LocalDateTime.now(), TEXT_1, USER_ID);
	}

	@Test
	public void getMessages_succes()
	{
		when(chatMessageRepository.findAll()).thenReturn(Collections.singletonList(chatMessage));

		List<ChatMessageRq> messageRq = service.getMessages();

		assertThat(messageRq).isNotNull();
		assertThat(messageRq.size()).isEqualTo(1);
		assertThat(messageRq.get(0).getId()).isEqualTo(UUID.fromString(MESSAGE_ID));
	}

	@Test
	public void getMessages_empty()
	{
		when(chatMessageRepository.findAll()).thenReturn(Collections.emptyList());

		List<ChatMessageRq> messageRq = service.getMessages();

		assertThat(messageRq).isNotNull();
		assertThat(messageRq.isEmpty()).isEqualTo(true);

	}

	@Test
	public void saveMessage_succes()
	{
		when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

		String id = service.saveMessage(chatMessageRq);

		assertThat(id).isNotNull();
		assertThat(id).isEqualTo(MESSAGE_ID);
	}

}