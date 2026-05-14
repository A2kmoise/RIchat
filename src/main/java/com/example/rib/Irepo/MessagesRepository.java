package com.example.rib.Irepo;

import com.example.rib.Imodel.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, String> {
    List<Messages> findByConversationIdOrderByCreatedAtAsc(String conversationId);
}
