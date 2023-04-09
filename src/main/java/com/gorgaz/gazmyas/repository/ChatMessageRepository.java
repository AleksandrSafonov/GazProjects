package com.gorgaz.gazmyas.repository;

import com.gorgaz.gazmyas.model.ChatMessage;

import java.time.LocalDateTime;
import java.util.*;

import static com.gorgaz.gazmyas.repository.ChatUserRepository.USER_ID;

public class ChatMessageRepository implements IChatMessageRepository {

    private static final Map<String, ChatMessage> chatMessages = new Hashtable<>();

    static {
        String id = "01e446d6-7ab4-4b16-8a5e-ec6f4d635b4e";
        chatMessages.put(id, new ChatMessage(id,
                LocalDateTime.of(2023, 4, 3, 8, 0, 1),
                "Всем привет", USER_ID));
        id = "131ec463-ab0d-451a-8a35-442cc71976f4";
        chatMessages.put(id, new ChatMessage(id,
                LocalDateTime.of(2022, 4, 3, 8, 0, 1),
                "Всем привет", USER_ID));
    }

    public List<ChatMessage> findAll() {
        return chatMessages.values().stream().toList();
    }

    public ChatMessage findById(String id) {
        return chatMessages.get(id);
    }

    public List<ChatMessage> findAllAfterDate(LocalDateTime startDate) {
        return chatMessages.values().stream()
                .filter(c -> c.getDateTime().isAfter(startDate))
                .toList();
    }

    public ChatMessage save(ChatMessage message) {
        chatMessages.put(message.getId(), message);
        return message;
    }

    public void clear() {
        chatMessages.clear();
    }

    public void remove(String id) {
        chatMessages.remove(id);
    }

    public boolean existById(String id) {
        return chatMessages.containsKey(id);
    }
}