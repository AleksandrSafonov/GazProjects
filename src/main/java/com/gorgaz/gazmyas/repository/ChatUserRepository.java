package com.gorgaz.gazmyas.repository;

import com.gorgaz.gazmyas.model.ChatMessage;
import com.gorgaz.gazmyas.model.ChatUser;

import java.util.*;

public class ChatUserRepository implements IChatUserRepository {

    public static final String USER_ID = "85c48b2d-3de5-463e-9709-ae468f3c328f";
    private static final Map<String, ChatUser> users = new Hashtable<>();

    static {
        ChatUser chatUser = new ChatUser("Иван да Марья");
        chatUser.setActive(false);
        chatUser.setId(USER_ID);
        users.put(USER_ID, chatUser);
    }

    public List<ChatUser> findAll() {
        return users.values().stream().toList();
    }

    public List<ChatUser> findAllActive() {
        return users.values().stream().filter(ChatUser::isActive).toList();
    }

    public ChatUser findById(String id) {
        return users.get(id);
    }

    public boolean existById(String id) {
        return users.containsKey(id);
    }

    public ChatUser save(ChatUser user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        users.put(user.getId(), user);
        return user;
    }

    public void clear() {
        users.clear();
    }

    public void remove(String id) {
        users.remove(id);
    }

}