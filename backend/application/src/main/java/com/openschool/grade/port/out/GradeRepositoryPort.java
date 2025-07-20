package com.openschool.grade.port.out;

import com.openschool.domain.grade.Grade;

public interface GradeRepositoryPort {
    Grade createGrade(Grade grade);
}
