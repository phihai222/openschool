package com.openschool.infrastructure.adapter.out.persistence.academic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semester")
public class SemesterEntity {
    @Id
    private UUID id;
    private String name;  // e.g. Semester 1, Spring Semester
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYearEntity academicYear;
}
