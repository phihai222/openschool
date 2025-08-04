package com.openschool.infrastructure.adapter.in.rest.systemsetup.dto;

import com.openschool.academic.port.in.command.CreateSemesterCommand;
import com.openschool.domain.academic.AcademicYearStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateAcademicYearRequest {
    private UUID schoolId;
    private String code;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    private AcademicYearStatus status;

    private List<CreateSemesterCommand> semesters;
}
