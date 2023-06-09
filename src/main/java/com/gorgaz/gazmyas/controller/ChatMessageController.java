package com.gorgaz.gazmyas.controller;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.service.IChatMessageService;
import com.gorgaz.gazmyas.utils.ChatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class ChatMessageController {

	private final IChatMessageService chatMessageService;

	@GetMapping("/getChatMessages")
	public ResponseEntity<List<ChatMessageRq>> getChatMessages() {
		log.info("getChatMessages started");
		return ResponseEntity.ok(chatMessageService.getMessages());
	}

	@GetMapping("/getChatMessagesAfterDate")
	public ResponseEntity<List<ChatMessageRq>> getChatMessagesAfterDate(@RequestParam LocalDateTime dateStart) {
		log.info("getChatMessagesAfterDate started");
		return ResponseEntity.ok(chatMessageService.getMessagesAfterDate(dateStart));
	}

	@GetMapping("/getChatMessage")
	public ResponseEntity<ChatMessageRq> getChatMessage(@RequestParam String messageId) {
		log.info("getChatMessage started");

		if (!ChatUtil.isValidUUID(messageId)) {
			log.info("getChatMessage: Не валидный messageId");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!chatMessageService.existById(messageId)) {
			log.info("Отсутствует сообщение");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(chatMessageService.getMessage(messageId));
	}

	@PostMapping(value = "/addChatMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addChatMessage(@RequestBody final ChatMessageRq rq) {
		log.info("addChatMessage started");
		try {
			String chatMessageId = chatMessageService.saveMessage(rq);
			return ResponseEntity.ok(chatMessageId);
		} catch (Exception e) {
			log.error("Ошибка добавления сообщения: ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/removeChatMessage")
	public ResponseEntity<Void> removeChatMessage(@RequestBody String id) {
		log.info("removeChatMessage started");
		if (!chatMessageService.existById(id)) {
			log.info("Попытка удаления несуществующего сообщения");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			chatMessageService.removeMessage(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ошибка удаления сообщения: ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/removeAllChatMessages")
	public ResponseEntity<Void> removeAllChatMessages() {
		log.info("removeAllChatMessages started");
		try {
			chatMessageService.removeAllChatMessage();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Ошибка удаления сообщения: ", e);
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}