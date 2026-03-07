package com.example.rib.Imodel;

import com.example.rib.Ienum.ConversationType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type" , nullable = false)
    private ConversationType conversationType;

    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    private Instant createdAt;
    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ConversationParticipants> conversationParticipants;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public ConversationType getConversationType() { return conversationType; }
    public void setConversationType(ConversationType conversationType) { this.conversationType = conversationType; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public List<ConversationParticipants> getConversationParticipants() {
        return conversationParticipants;
    }
    public void setConversationParticipants(List<ConversationParticipants> conversationParticipants) {
        this.conversationParticipants = conversationParticipants;
    }


}
