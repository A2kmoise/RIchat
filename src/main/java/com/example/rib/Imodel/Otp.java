package com.example.rib.Imodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "otp")
@Getter
@Setter
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column( nullable = false)
    private String otp;

    @Column( nullable = false)
    private String email;

    @Column( nullable = false)
    private Instant createdAt;

    @Column( nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean used = false;

    public Otp(){}

    public Otp(String otp, String email, Instant createdAt, Instant expiresAt){
        this.otp = otp;
        this.email = email;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

}
