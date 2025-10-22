package com.example.websocket_one_to_one.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer > {
    List<ChatMessage> findByChatId(String e);
}
