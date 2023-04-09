package com.gorgaz.gazmyas.controller;

import com.gorgaz.gazmyas.gererated.model.ChatUserRq;
import com.gorgaz.gazmyas.service.IChatUserService;
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

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatUserRestControllerTest
{
	@InjectMocks
	ChatUserController controller;

	@Mock
	IChatUserService chatUserService;

	private static final String USER_ID = "00000000-0000-1111-1111-111111111111";
	private static final String NAME = "Имя 1";

	ChatUserRq chatUserRq;

	@BeforeEach
	void init() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		chatUserRq = new ChatUserRq(
				UUID.fromString(USER_ID),
				NAME);
		chatUserRq.setActive(Boolean.TRUE);
	}

	@Test
	public void addChatUser_succes()
	{
		when(chatUserService.saveUser(any(ChatUserRq.class))).thenReturn(USER_ID);

		ResponseEntity<String> responseEntity = controller.addChatUser(chatUserRq);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(USER_ID);
	}

	@Test
	public void addChatUser_error()
	{
		when(chatUserService.saveUser(any(ChatUserRq.class))).thenThrow(NullPointerException.class);

		ResponseEntity<String> responseEntity = controller.addChatUser(chatUserRq);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void removeChatUser_succes()
	{
		when(chatUserService.existById(anyString())).thenReturn(true);

		ResponseEntity<Void> responseEntity = controller.removeChatUser(USER_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void removeChatUser_error()
	{
		when(chatUserService.existById(anyString())).thenReturn(true);
		doThrow(NullPointerException.class).when(chatUserService)
				.removeUser(anyString());

		ResponseEntity<Void> responseEntity = controller.removeChatUser(USER_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void removeChatUser_not_found()
	{
		when(chatUserService.existById(anyString())).thenReturn(false);

		ResponseEntity<Void> responseEntity = controller.removeChatUser(USER_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	public void getChatUser_success()
	{
		when(chatUserService.getUser(anyString())).thenReturn(chatUserRq);

		ResponseEntity<ChatUserRq> responseEntity = controller.getChatUser(USER_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId().toString()).isEqualTo(USER_ID);
	}

	@Test
	public void getChatUser_noValid()
	{
		ResponseEntity<ChatUserRq> responseEntity = controller.getChatUser("32323");

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getChatUser_noFound()
	{
		when(chatUserService.getUser(anyString())).thenReturn(null);

		ResponseEntity<ChatUserRq> responseEntity = controller.getChatUser(USER_ID);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	public void getChatUsers()
	{
		when(chatUserService.getUsers(true)).thenReturn(Collections.singletonList(chatUserRq));

		ResponseEntity<List<ChatUserRq>> responseEntity = controller.getChatUsers(true);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().size()).isEqualTo(1);
		assertThat(responseEntity.getBody().get(0).getId().toString()).isEqualTo(USER_ID);
	}

	@Test
	public void getChatUsers_empty()
	{
		when(chatUserService.getUsers(true)).thenReturn(Collections.emptyList());

		ResponseEntity<List<ChatUserRq>> responseEntity = controller.getChatUsers(true);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().isEmpty()).isEqualTo(true);
	}

}