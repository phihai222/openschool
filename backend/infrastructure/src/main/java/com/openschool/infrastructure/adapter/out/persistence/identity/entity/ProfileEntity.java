package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "identity_profile")
public class ProfileEntity {
    @Id
    private UUID id;
    private UUID identityId;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Instant createdAt;
    private Instant updatedAt;
}
