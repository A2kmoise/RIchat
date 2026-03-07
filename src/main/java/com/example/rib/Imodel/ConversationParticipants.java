package com.example.rib.Imodel;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "conversationParticipants")
public class ConversationParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "conversation_id", nullable = false)
   private Conversation conversation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "joinedAt", nullable = false)
    @CreationTimestamp
    private Instant joinedAt;


}
