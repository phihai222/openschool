package com.openschool.domain.academic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Semester {
    private UUID id;
    private String name;  // e.g. Semester 1, Spring Semester
    private LocalDate startDate;
    private LocalDate endDate;
}
