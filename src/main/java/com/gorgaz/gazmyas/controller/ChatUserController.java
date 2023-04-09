package com.gorgaz.gazmyas.controller;

import com.gorgaz.gazmyas.gererated.model.ChatUserRq;
import com.gorgaz.gazmyas.service.IChatUserService;
import com.gorgaz.gazmyas.utils.ChatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ChatUserController {

	private final IChatUserService chatUserService;

	@GetMapping("/getChatUsers")
	public ResponseEntity<List<ChatUserRq>> getChatUsers(@RequestParam boolean onlyActive) {
		log.info("getChatUsers started");
		return ResponseEntity.ok(chatUserService.getUsers(onlyActive));
	}

	@GetMapping("/getChatUser")
	public ResponseEntity<ChatUserRq> getChatUser(@RequestParam String userId) {
		log.info("getChatUser started");

		if (!ChatUtil.isValidUUID(userId)) {
			log.info("getChatUser: Не валидный userId");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(chatUserService.getUser(userId));
	}

	@PostMapping(value = "/addChatUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addChatUser(@RequestBody final ChatUserRq rq) {
		log.info("addChatUser started");
		try {
			String chatMessageId = chatUserService.saveUser(rq);
			return ResponseEntity.ok(chatMessageId);
		} catch (Exception e) {
			log.error("Ошибка добавления участника чата: ", e);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping(value = "/removeChatUser")
	public ResponseEntity<Void> removeChatUser(@RequestBody String id) {
		log.info("removeChatUser started");
		if (!chatUserService.existById(id)) {
			log.info("Попытка удаления несуществующего сообщения");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			chatUserService.removeUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ошибка удаления сообщения: ", e);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping(value = "/removeAllChatUsers")
	public ResponseEntity<Void> removeAllChatUsers() {
		log.info("removeAllChatUsers started");
		try {
			chatUserService.removeAllUser();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ошибка удаления сообщения: ", e);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}