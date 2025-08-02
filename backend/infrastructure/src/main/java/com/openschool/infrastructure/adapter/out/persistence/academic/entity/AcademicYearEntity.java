package com.openschool.infrastructure.adapter.out.persistence.academic.entity;

import com.openschool.domain.academic.AcademicYear;
import com.openschool.domain.academic.AcademicYearStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "academic_year")
public class AcademicYearEntity {
    @Id
    private UUID id;

    private String code;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private AcademicYearStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private List<SemesterEntity> semesters;

    public static AcademicYearEntity fromDomain(com.openschool.domain.academic.AcademicYear domain) {
        AcademicYearEntity entity = new AcademicYearEntity();
        entity.setId(domain.getId());
        entity.setCode(domain.getCode());
        entity.setName(domain.getName());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setStatus(domain.getStatus());
        if (domain.getSemesters() != null) {
            List<SemesterEntity> semesterEntities = domain.getSemesters().stream()
                .map(s -> {
                    SemesterEntity se = new SemesterEntity();
                    se.setId(s.getId());
                    se.setName(s.getName());
                    se.setStartDate(s.getStartDate());
                    se.setEndDate(s.getEndDate());
                    se.setAcademicYear(entity);
                    return se;
                })
                .toList();
            entity.setSemesters(semesterEntities);
        }
        return entity;
    }

    public AcademicYear toDomain() {
        com.openschool.domain.academic.AcademicYear.AcademicYearBuilder builder = com.openschool.domain.academic.AcademicYear.builder();
        builder.id(this.id)
            .code(this.code)
            .name(this.name)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .status(this.status);
        if (this.semesters != null) {
            List<com.openschool.domain.academic.Semester> semesterDomains = this.semesters.stream()
                .map(se -> com.openschool.domain.academic.Semester.builder()
                    .id(se.getId())
                    .name(se.getName())
                    .startDate(se.getStartDate())
                    .endDate(se.getEndDate())
                    .build())
                .toList();
            builder.semesters(semesterDomains);
        }
        return builder.build();
    }
}
