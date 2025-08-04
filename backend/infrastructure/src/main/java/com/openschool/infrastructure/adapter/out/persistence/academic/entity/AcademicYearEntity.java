package com.openschool.infrastructure.adapter.out.persistence.academic.entity;

import com.openschool.domain.academic.AcademicYear;
import com.openschool.domain.academic.AcademicYearStatus;
import com.openschool.domain.academic.Semester;
import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private SchoolEntity school;

    private String code;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private AcademicYearStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private List<SemesterEntity> semesters;

    public static AcademicYearEntity fromDomain(AcademicYear domain, SchoolEntity school) {
        AcademicYearEntity entity = new AcademicYearEntity();
        entity.setSchool(school);
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
        AcademicYear academicYear = AcademicYear.builder()
                .id(this.id)
                .schoolId(this.school.getId())
                .code(this.code)
                .name(this.name)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .status(this.status)
                .build();
        if (this.semesters != null) {
            List<Semester> semesterDomains = this.semesters.stream()
                    .map(se -> Semester.builder()
                            .id(se.getId())
                            .name(se.getName())
                            .startDate(se.getStartDate())
                            .endDate(se.getEndDate())
                            .build())
                    .toList();
            academicYear.setSemesters(semesterDomains);
        }
        return academicYear;
    }
}
