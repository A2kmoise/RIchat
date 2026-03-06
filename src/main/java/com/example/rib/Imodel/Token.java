package com.example.rib.Imodel;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "refreshtokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "expiryDate", nullable = false)
    private Instant expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", unique = true, nullable = false)
    private User user;

    public String getId() { return id;}
    public void setId(String id) { this.id = id;}
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token;}
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt;}
    public Instant getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Instant expiryDate) { this.expiryDate = expiryDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
