package com.example.rib.Imodel;

import com.example.rib.Ienum.MessagesStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    @Column(name = "sentAt")
    private Instant createdAt;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private MessagesStatus messageType;

    @UpdateTimestamp
    @Column(name = "editedAt")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessagesStatus getMessageType() {
        return messageType;
    }

    public void setMessageType(MessagesStatus messageType) {
        this.messageType = messageType;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }

    @OneToMany(mappedBy = "messageSource")
    private List<Attachments> attachments;
}
