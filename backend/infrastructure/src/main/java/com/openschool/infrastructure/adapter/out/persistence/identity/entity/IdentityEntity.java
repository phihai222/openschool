package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "identity")
public class IdentityEntity {
    @Id
    private UUID id;
}