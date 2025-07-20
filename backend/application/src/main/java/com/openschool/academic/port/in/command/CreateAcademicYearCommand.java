package com.openschool.academic.port.in.command;

import com.openschool.domain.academic.AcademicYearStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateAcademicYearCommand {
    private String code;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    private AcademicYearStatus status;

    private List<CreateSemesterCommand> semesters;
}
