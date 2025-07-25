package com.openschool.domain.systemsetup;

public enum SetupStep {
    CREATE_ADMIN_USER,
    CREATE_SCHOOL,
    CREATE_ACADEMIC_YEAR,
    CREATE_GRADES,
    FINISH;

    public SetupStep next() {
        return switch (this) {
            case CREATE_ADMIN_USER -> CREATE_SCHOOL;
            case CREATE_SCHOOL -> CREATE_ACADEMIC_YEAR;
            case CREATE_ACADEMIC_YEAR -> CREATE_GRADES;
            case CREATE_GRADES, FINISH -> FINISH;
        };
    }
}
