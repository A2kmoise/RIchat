package com.example.rib.Irepo;

import com.example.rib.Ienum.ConversationType;
import com.example.rib.Imodel.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository
        extends JpaRepository<Conversation, String> {


    List<Conversation> findByConversationType(
            ConversationType conversationType
    );


    @Query("""
        SELECT c
        FROM Conversation c
        JOIN c.conversationParticipants cp
        WHERE c.conversationType = :type
        AND cp.user.id IN (:senderId, :receiverId)
        GROUP BY c.id
        HAVING COUNT(DISTINCT cp.user.id) = 2
    """)
    Optional<Conversation> findPrivateConversationBetweenUsers(
            @Param("senderId") String senderId,
            @Param("receiverId") String receiverId,
            @Param("type") ConversationType type
    );
}