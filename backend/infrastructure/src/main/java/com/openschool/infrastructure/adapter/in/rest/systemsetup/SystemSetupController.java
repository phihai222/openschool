package com.openschool.infrastructure.adapter.in.rest.systemsetup;

import com.openschool.grade.port.in.command.CreateGradeCommand;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.*;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.mapper.SystemSetupDtoMapper;
import com.openschool.systemsetup.port.in.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/system-setup")
public class SystemSetupController {
    private final GetSystemSetupStatusUseCase getSystemSetupStatusUseCase;
    private final SetupAdminUseCase setupAdminUseCase;
    private final SetupSchoolProfileUseCase setupSchoolProfileUseCase;
    private final SetupAcademicYearUseCase setupAcademicYearUseCase;
    private final SetupGradeUseCase setupGradeUseCase;

    public SystemSetupController(
            @Qualifier("getSystemSetupStatusUseCase") GetSystemSetupStatusUseCase getSystemSetupStatusUseCase,
            @Qualifier("setupAdminUseCase") SetupAdminUseCase setupAdminUseCase,
            @Qualifier("setupSchoolProfileUseCase") SetupSchoolProfileUseCase setupSchoolProfileUseCase,
            @Qualifier("setupAcademicYearUseCase") SetupAcademicYearUseCase setupAcademicYearUseCase,
            @Qualifier("setupGradeUseCase") SetupGradeUseCase setupGradeUseCase) {
        this.getSystemSetupStatusUseCase = getSystemSetupStatusUseCase;
        this.setupAdminUseCase = setupAdminUseCase;
        this.setupSchoolProfileUseCase = setupSchoolProfileUseCase;
        this.setupAcademicYearUseCase = setupAcademicYearUseCase;
        this.setupGradeUseCase = setupGradeUseCase;
    }

    @GetMapping("/status")
    public ResponseEntity<SystemSetupStatusResponse> getSystemSetupStatus() {
        var res = getSystemSetupStatusUseCase.getSystemSetupStatus();
        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }

    @PostMapping("/init-root-user")
    public ResponseEntity<SystemSetupStatusResponse> initRootUser(@RequestBody CreateAdminRequest request) {
        var res = setupAdminUseCase.createAdminUser(
                SystemSetupDtoMapper.toCommand(request)
        );

        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }

    @PostMapping("/create-school-profile")
    public ResponseEntity<SystemSetupStatusResponse> createSchoolProfile(@RequestBody CreateSchoolRequest request) {
        var res = setupSchoolProfileUseCase.createSchoolProfile(
                SystemSetupDtoMapper.toCommand(request)
        );

        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }

    @PostMapping("/create-academic-year")
    public ResponseEntity<SystemSetupStatusResponse> createAcademicYear(@RequestBody CreateAcademicYearRequest request) {
        // Map the request to the command
        var res = setupAcademicYearUseCase.create(SystemSetupDtoMapper.toCommand(request));
        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }

    @PostMapping("/create-grades")
    public ResponseEntity<SystemSetupStatusResponse> createGrades(@RequestBody List<CreateGradeRequest> request) {
        // Map the request to the command and create grades
        List<CreateGradeCommand> commands = request.stream()
                .map(SystemSetupDtoMapper::toCommand)
                .toList();

        // Call the use case to create grades
        var res = setupGradeUseCase.createGrades(commands);
        return ResponseEntity.ok(SystemSetupDtoMapper.toResponse(res));
    }
}
