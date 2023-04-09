package com.gorgaz.gazmyas.mapper;

import com.gorgaz.gazmyas.gererated.model.ChatMessageRq;
import com.gorgaz.gazmyas.model.ChatMessage;

import java.util.List;
import java.util.UUID;

public class ChatMessageMapper {

    public static ChatMessageRq toRq(ChatMessage message) {
        if (message == null) {
            return null;
        }
        return new ChatMessageRq(
                UUID.fromString(message.getId()),
                UUID.fromString(message.getUserId()),
                message.getDateTime())
                .text(message.getText());
    }

    public static ChatMessage toChatMessage(ChatMessageRq rq) {
        if (rq == null) {
            return null;
        }
        return new ChatMessage(rq.getId().toString(),
                rq.getDateTime(),
                rq.getText(),
                rq.getUserId().toString());
    }

    public static List<ChatMessage> toChatMessages(List<ChatMessageRq> rqs) {
        if (rqs == null) {
            return null;
        }
        return rqs.stream().map(ChatMessageMapper::toChatMessage).toList();
    }

    public static List<ChatMessageRq> toRqs(List<ChatMessage> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream().map(ChatMessageMapper::toRq).toList();
    }

}
