package com.openschool.academic.port.in.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateSemesterCommand {
    private String name;  // e.g. Semester 1, Spring Semester
    private LocalDate startDate;
    private LocalDate endDate;
}
