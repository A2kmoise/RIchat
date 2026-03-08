package com.example.rib.Irepo;

import com.example.rib.Imodel.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
}
