package com.openschool.domain.grade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Grade {
    private UUID id;
    private UUID schoolId;

    private String name;
    private String code;
    private GradeLevel level;

    private Integer minAge;
    private Integer maxAge;

    private Integer displayOrder;
    private boolean allowClass;

    private GradeStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
