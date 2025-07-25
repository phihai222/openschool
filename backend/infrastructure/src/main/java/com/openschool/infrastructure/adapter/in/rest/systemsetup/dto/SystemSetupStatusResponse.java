package com.openschool.infrastructure.adapter.in.rest.systemsetup.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemSetupStatusResponse {
    private String currentStep;
    private boolean completed;
    private Steps steps;

    public SystemSetupStatusResponse(String currentStep, boolean completed, Steps steps) {
        this.currentStep = currentStep;
        this.completed = completed;
        this.steps = steps;
    }

    @Setter
    @Getter
    public static class Steps {
        private boolean adminCreated;
        private boolean schoolInfoCreated;
        private boolean academicYearCreated;
        private boolean gradeCreated;

        public Steps(boolean adminCreated, boolean schoolInfoCreated, boolean academicYearCreated, boolean gradeCreated) {
            this.adminCreated = adminCreated;
            this.schoolInfoCreated = schoolInfoCreated;
            this.academicYearCreated = academicYearCreated;
            this.gradeCreated = gradeCreated;
        }

    }
}
