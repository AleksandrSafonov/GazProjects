package com.gorgaz.gazmyas.service;

import com.gorgaz.gazmyas.gererated.model.ChatUserRq;
import com.gorgaz.gazmyas.mapper.ChatUserMapper;
import com.gorgaz.gazmyas.model.ChatUser;
import com.gorgaz.gazmyas.repository.ChatUserRepository;
import com.gorgaz.gazmyas.repository.IChatUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatUserService implements IChatUserService {
	
	IChatUserRepository chatUserRepository = new ChatUserRepository();

	public List<ChatUserRq> getUsers(boolean onlyActive) {
		List<ChatUser> chatUsers;
		if (onlyActive){
			chatUsers = chatUserRepository.findAllActive();
		} else {
			chatUsers = chatUserRepository.findAll();
		}
		return ChatUserMapper.toRqs(chatUsers);
	}

	public ChatUserRq getUser(String id) {
		ChatUser chatUser = chatUserRepository.findById(id);
		return ChatUserMapper.toRq(chatUser);
	}

	public boolean existById(String id) {
		return chatUserRepository.existById(id);
	}

	public String saveUser(ChatUserRq rq) {
		ChatUser user = ChatUserMapper.toChatUser(rq);
		return chatUserRepository.save(user).getId();
	}

	public void removeUser(String id) {
		chatUserRepository.remove(id);
	}

	public void removeAllUser() {
		chatUserRepository.clear();
	}

}
