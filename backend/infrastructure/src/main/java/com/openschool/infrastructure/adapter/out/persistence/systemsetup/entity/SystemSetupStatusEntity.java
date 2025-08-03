package com.openschool.infrastructure.adapter.out.persistence.systemsetup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Data
@Table(name = "system_setup_status")
public class SystemSetupStatusEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "school_id")
    private UUID schoolId;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "current_step", nullable = false)
    private String currentStep;

    @Column(name = "admin_created", nullable = false)
    private boolean adminCreated;

    @Column(name = "school_created", nullable = false)
    private boolean schoolCreated;

    @Column(name = "year_created", nullable = false)
    private boolean yearCreated;

    @Column(name = "grade_created", nullable = false)
    private boolean gradeCreated;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
