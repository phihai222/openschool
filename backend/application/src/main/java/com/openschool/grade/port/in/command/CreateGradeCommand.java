package com.openschool.grade.port.in.command;

import com.openschool.domain.grade.GradeLevel;
import com.openschool.domain.grade.GradeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGradeCommand {
    private String name;
    private String code;
    private GradeLevel level;

    private Integer minAge;
    private Integer maxAge;

    private Integer displayOrder;
    private boolean allowClass;

    private GradeStatus status;
}
