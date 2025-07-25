package com.openschool.domain.systemsetup;

import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Getter
@Setter
public class SystemSetupStatus {
    private SetupStep currentStep;
    private boolean isCompleted;

    private Map<SetupStep, Boolean> steps = new EnumMap<>(SetupStep.class);

    public SystemSetupStatus() {
        for (SetupStep step : SetupStep.values()) {
            steps.put(step, false);
        }
        this.currentStep = SetupStep.CREATE_ADMIN_USER;
        this.isCompleted = false;
    }

    public void markStepCompleted(SetupStep step) {
        steps.put(step, true);

        this.currentStep = step.next();
        this.isCompleted = steps.values().stream().allMatch(Boolean::booleanValue);
    }
}
