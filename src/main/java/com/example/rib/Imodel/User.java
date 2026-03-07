package com.example.rib.Imodel;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private String id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Instant updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Token token;

    @OneToMany(mappedBy = "user")
    private List<Conversation> conversation;

    @OneToMany(mappedBy = "sender")
    private List<Messages> messages;

    public String getId() { return id; }
    public void setId(String id) { this.id = id;}
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt;}
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public Token getToken() { return token; }
    public void setToken(Token token) { this.token = token; }
    public List<Conversation> getConversation() { return conversation; }
    public void setConversation(List<Conversation> conversation) { this.conversation = conversation; }

    public List<Messages> getMessages() { return messages; }
    public void setMessages(List<Messages> messages) { this.messages = messages; }


}
