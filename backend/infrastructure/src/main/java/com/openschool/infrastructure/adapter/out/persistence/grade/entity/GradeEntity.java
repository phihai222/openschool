package com.openschool.infrastructure.adapter.out.persistence.grade.entity;

import com.openschool.domain.grade.Grade;
import com.openschool.domain.grade.GradeLevel;
import com.openschool.domain.grade.GradeStatus;
import com.openschool.infrastructure.adapter.out.persistence.constant.BaseEntity;
import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grade")
public class GradeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private SchoolEntity school;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeLevel level;

    private Integer minAge;
    private Integer maxAge;
    private Integer displayOrder;
    private boolean allowClass;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeStatus status;

    public static GradeEntity fromDomain(Grade grade, SchoolEntity school) {
        if (grade == null || school == null) {
            return null;
        }
        return GradeEntity.builder()
                .id(grade.getId())
                .school(school)
                .name(grade.getName())
                .code(grade.getCode())
                .level(grade.getLevel())
                .minAge(grade.getMinAge())
                .maxAge(grade.getMaxAge())
                .displayOrder(grade.getDisplayOrder())
                .allowClass(grade.isAllowClass())
                .status(grade.getStatus())
                .build();
    }

    public Grade toDomain() {
        return Grade.builder()
                .id(this.id)
                .schoolId(this.school != null ? this.school.getId() : null)
                .name(this.name)
                .code(this.code)
                .level(this.level)
                .minAge(this.minAge)
                .maxAge(this.maxAge)
                .displayOrder(this.displayOrder)
                .allowClass(this.allowClass)
                .status(this.status)
                .build();
    }
}
