package com.devjefster.backoffice.util.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Instant createdAt;

    @Version
    @UpdateTimestamp
    private Instant updatedAt;

    public EntidadeBase() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }


    @PrePersist
    public void onInsert() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }


    @PreUpdate
    public void onUpdate() {
        updatedAt = Instant.now();
    }
}
