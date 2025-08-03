package com.openschool.infrastructure.adapter.in.rest.systemsetup.dto;

import com.openschool.domain.grade.GradeLevel;
import com.openschool.domain.grade.GradeStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGradeRequest {
    private UUID schoolId;

    private String name;
    private String code;
    private GradeLevel level;

    private Integer minAge;
    private Integer maxAge;

    private Integer displayOrder;
    private boolean allowClass;

    private GradeStatus status;
}
