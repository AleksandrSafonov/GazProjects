package com.gorgaz.gazmyas.mapper;

import com.gorgaz.gazmyas.gererated.model.ChatUserRq;
import com.gorgaz.gazmyas.model.ChatUser;

import java.util.List;
import java.util.UUID;

public class ChatUserMapper {

    public static ChatUserRq toRq(ChatUser user) {
        if (user == null) {
            return null;
        }
        return new ChatUserRq(
                UUID.fromString(user.getId()),
                user.getName())
                .active(user.isActive());
    }

    public static ChatUser toChatUser(ChatUserRq rq) {
        if (rq == null) {
            return null;
        }
        ChatUser chatUser = new ChatUser(rq.getName());
        if (rq.getActive() != null || rq.getActive()) {
            chatUser.setActive(true);
        }
        if (rq.getId() != null) {
            chatUser.setId(rq.getId().toString());
        }
        return chatUser;
    }

    public static List<ChatUser> toChatUsers(List<ChatUserRq> rqs) {
        if (rqs == null) {
            return null;
        }
        return rqs.stream().map(ChatUserMapper::toChatUser).toList();
    }

    public static List<ChatUserRq> toRqs(List<ChatUser> users) {
        if (users == null) {
            return null;
        }
        return users.stream().map(ChatUserMapper::toRq).toList();
    }

}
