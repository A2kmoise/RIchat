package com.example.rib.Imodel;

import com.example.rib.Ienum.AttachmentType;
import jakarta.persistence.*;

@Entity
@Table(name="attachments")
public class Attachments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "attachmentType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AttachmentType type;

    @Column(name = "source", nullable = false)
    private  String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conversation_id", nullable = false)
    private Messages messageSource;

}
