package com.gorgaz.gazmyas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.service.IChatMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class ChatMessageRestControllerTest
{
	@InjectMocks
	ChatMessageController controller;

	@Mock
	IChatMessageService chatMessageService;

	private static final String MESSAGE_ID = "931ec463-ab0d-451a-8a35-442cc71976f4";
	private static final String USER_ID = "00000000-0000-1111-1111-111111111111";
	private static final String TEXT_1 = "Тест1";

	ChatMessageRq chatMessageRq;

	@BeforeEach
	void init() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		chatMessageRq = new ChatMessageRq(
				UUID.fromString(MESSAGE_ID),
				UUID.fromString(USER_ID),
				LocalDateTime.now())
				.text(TEXT_1);
	}

	@Test
	public void addChatMessage_succes()
	{
		when(chatMessageService.saveMessage(any(ChatMessageRq.class))).thenReturn(MESSAGE_ID);

		ResponseEntity<String> responseEntity = controller.addChatMessage(chatMessageRq);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(MESSAGE_ID);
	}

	@Test
	public void addChatMessage_error()
	{
		when(chatMessageService.saveMessage(any(ChatMessageRq.class))).thenThrow(NullPointerException.class);

		ResponseEntity<String> responseEntity = controller.addChatMessage(chatMessageRq);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void removeChatMessage_succes()
	{
		when(chatMessageService.existById(anyString())).thenReturn(true);

		ResponseEntity<Void> responseEntity = controller.removeChatMessage(MESSAGE_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void removeChatMessage_error()
	{
		when(chatMessageService.existById(anyString())).thenReturn(true);
		doThrow(NullPointerException.class).when(chatMessageService)
				.removeMessage(anyString());

		ResponseEntity<Void> responseEntity = controller.removeChatMessage(MESSAGE_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void removeChatMessage_not_found()
	{
		when(chatMessageService.existById(anyString())).thenReturn(false);

		ResponseEntity<Void> responseEntity = controller.removeChatMessage(MESSAGE_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	public void getChatMessage_success()
	{
		when(chatMessageService.getMessage(anyString())).thenReturn(chatMessageRq);

		ResponseEntity<ChatMessageRq> responseEntity = controller.getChatMessage(MESSAGE_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId().toString()).isEqualTo(MESSAGE_ID);
	}

	@Test
	public void getChatMessage_noValid()
	{
		ResponseEntity<ChatMessageRq> responseEntity = controller.getChatMessage("32323");

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getChatMessage_noFound()
	{
		when(chatMessageService.getMessage(anyString())).thenReturn(null);

		ResponseEntity<ChatMessageRq> responseEntity = controller.getChatMessage(MESSAGE_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	public void getChatMessages()
	{
		when(chatMessageService.getMessages()).thenReturn(Collections.singletonList(chatMessageRq));

		ResponseEntity<List<ChatMessageRq>> responseEntity = controller.getChatMessages();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().size()).isEqualTo(1);
		assertThat(responseEntity.getBody().get(0).getId().toString()).isEqualTo(MESSAGE_ID);
	}

	@Test
	public void getChatMessages_empty()
	{
		when(chatMessageService.getMessages()).thenReturn(Collections.emptyList());

		ResponseEntity<List<ChatMessageRq>> responseEntity = controller.getChatMessages();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().isEmpty()).isEqualTo(true);
	}

	@Test
	public void getChatMessagesAfterDate()
	{
		when(chatMessageService.getMessagesAfterDate(any(LocalDateTime.class))).thenReturn(Collections.singletonList(chatMessageRq));

		ResponseEntity<List<ChatMessageRq>> responseEntity = controller.getChatMessagesAfterDate(LocalDateTime.now());

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().size()).isEqualTo(1);
		assertThat(responseEntity.getBody().get(0).getId().toString()).isEqualTo(MESSAGE_ID);
	}

}