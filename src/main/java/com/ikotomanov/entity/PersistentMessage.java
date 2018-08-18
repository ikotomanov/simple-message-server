package com.ikotomanov.entity;

import com.ikotomanov.model.MessageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name="MESSAGES")
@Getter @Setter
public class PersistentMessage {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    protected MessageType type;

    protected String payload;

    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @PrePersist
    private void setCreationTime() {
        createdAt = LocalDateTime.now();
    }
}
