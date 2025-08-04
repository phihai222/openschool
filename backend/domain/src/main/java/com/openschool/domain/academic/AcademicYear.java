package com.openschool.domain.academic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AcademicYear {
    private UUID id;
    private UUID schoolId;

    // e.g. "2025-2026"
    private String code;

    // e.g. "Academic Year 2025"
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    private AcademicYearStatus status;

    // Only for non-kindergarten
    private List<Semester> semesters;
}
