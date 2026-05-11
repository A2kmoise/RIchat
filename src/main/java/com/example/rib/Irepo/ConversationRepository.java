package com.example.rib.Irepo;

import com.example.rib.Ienum.ConversationType;
import com.example.rib.Imodel.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
    List<Conversation> conversationType(ConversationType conversationType);
}
