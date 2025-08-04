package com.openschool.systemsetup.service;

import com.openschool.academic.port.in.CreateAcademicYearUseCase;
import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.domain.school.School;
import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.grade.port.in.CreateGradeUseCase;
import com.openschool.grade.port.in.command.CreateGradeCommand;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.school.port.in.CreateSchoolUseCase;
import com.openschool.school.port.in.command.CreateSchoolCommand;
import com.openschool.common.exception.ForbiddenSetup;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SystemSetupServiceTest {

    @Mock
    private SystemSetupRepositoryPort systemSetupRepository;

    @Mock
    private InitRootUserUseCase initRootUserUseCase;

    @Mock
    private CreateSchoolUseCase createSchoolUseCase;

    @Mock
    private CreateAcademicYearUseCase createAcademicYearUseCase;

    @Mock
    private CreateGradeUseCase createGradeUseCase;

    @InjectMocks
    private SystemSetupService systemSetupService;

    private final UUID id = UUID.fromString("0c09669c-0e92-487b-8e33-df11ec6d8042");

    @BeforeEach
    void setUp() {
        try (var ignored = MockitoAnnotations.openMocks(this)) {
            systemSetupService = new SystemSetupService(systemSetupRepository, initRootUserUseCase, createSchoolUseCase, createAcademicYearUseCase, createGradeUseCase);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize mocks", e);
        }
    }

    @Test
    void testGetSystemSetupStatus_WhenStatusExists() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));

        SystemSetupStatus result = systemSetupService.getSystemSetupStatus();

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(systemSetupRepository, times(1)).getSystemSetupStatus(id);
    }

    @Test
    void testGetSystemSetupStatus_WhenStatusDoesNotExist() {
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.empty());
        when(systemSetupRepository.saveSystemStatus(any(SystemSetupStatus.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        SystemSetupStatus result = systemSetupService.getSystemSetupStatus();

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(systemSetupRepository, times(1)).saveSystemStatus(any(SystemSetupStatus.class));
    }

    @Test
    void testCreateAdminUser_WhenStepIsValid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_ADMIN_USER);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));
        when(systemSetupRepository.saveSystemStatus(any(SystemSetupStatus.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        CreateAdminCommand command = new CreateAdminCommand("admin", "password", "Admin User", "admin@example.com");

        SystemSetupStatus result = systemSetupService.createAdminUser(command);

        assertNotNull(result);
        assertTrue(result.getSteps().get(SetupStep.CREATE_ADMIN_USER));
        verify(initRootUserUseCase, times(1)).initRoot("admin", "password");
        verify(systemSetupRepository, times(1)).saveSystemStatus(any(SystemSetupStatus.class));
    }

    @Test
    void testCreateAdminUser_WhenStepIsInvalid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_SCHOOL);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));

        CreateAdminCommand command = new CreateAdminCommand("admin", "password", "Admin User", "admin@example.com");

        ForbiddenSetup exception = assertThrows(ForbiddenSetup.class, () -> systemSetupService.createAdminUser(command));

        assertEquals("Cannot create admin user at this step: CREATE_SCHOOL", exception.getMessage());
        verify(initRootUserUseCase, never()).initRoot(anyString(), anyString());
    }

    @Test
    void testCreateSchoolProfile_WhenStepIsValid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_SCHOOL);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));
        when(systemSetupRepository.saveSystemStatus(any(SystemSetupStatus.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        CreateSchoolCommand command = mock(CreateSchoolCommand.class);
        // Mock School object to avoid NullPointerException
        School mockSchool = School.builder()
                .id(UUID.randomUUID())
                .name("Test School")
                .build();
        when(createSchoolUseCase.create(command)).thenReturn(mockSchool);

        SystemSetupStatus result = systemSetupService.createSchoolProfile(command);

        assertNotNull(result);
        assertTrue(result.getSteps().get(SetupStep.CREATE_SCHOOL));
        verify(createSchoolUseCase, times(1)).create(command);
        verify(systemSetupRepository, times(1)).saveSystemStatus(any(SystemSetupStatus.class));
    }

    @Test
    void testCreateSchoolProfile_WhenStepIsInvalid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_ADMIN_USER);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));

        CreateSchoolCommand command = mock(CreateSchoolCommand.class);

        ForbiddenSetup exception = assertThrows(ForbiddenSetup.class, () -> systemSetupService.createSchoolProfile(command));

        assertEquals("Cannot create school profile at this step: CREATE_ADMIN_USER", exception.getMessage());
        verify(createSchoolUseCase, never()).create(any());
    }

    @Test
    void testCreateAcademicYear_WhenStepIsValid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_ACADEMIC_YEAR);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));
        when(systemSetupRepository.saveSystemStatus(any(SystemSetupStatus.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        CreateAcademicYearCommand command = mock(CreateAcademicYearCommand.class);

        SystemSetupStatus result = systemSetupService.create(command);

        assertNotNull(result);
        assertTrue(result.getSteps().get(SetupStep.CREATE_ACADEMIC_YEAR));
        verify(createAcademicYearUseCase, times(1)).create(command);
        verify(systemSetupRepository, times(1)).saveSystemStatus(any(SystemSetupStatus.class));
    }

    @Test
    void testCreateAcademicYear_WhenStepIsInvalid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_SCHOOL);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));

        CreateAcademicYearCommand command = mock(CreateAcademicYearCommand.class);

        ForbiddenSetup exception = assertThrows(ForbiddenSetup.class, () -> systemSetupService.create(command));

        assertEquals("Cannot create academic year at this step: CREATE_SCHOOL", exception.getMessage());
        verify(createAcademicYearUseCase, never()).create(any());
    }

    @Test
    void testCreateGrades_WhenStepIsValid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_GRADES);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));
        when(systemSetupRepository.saveSystemStatus(any(SystemSetupStatus.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(0)));

        // Mock CreateGradeCommand and list
        CreateGradeCommand gradeCommand1 = mock(CreateGradeCommand.class);
        CreateGradeCommand gradeCommand2 = mock(CreateGradeCommand.class);
        UUID schoolId = UUID.randomUUID();
        when(gradeCommand1.getSchoolId()).thenReturn(schoolId);
        when(gradeCommand2.getSchoolId()).thenReturn(schoolId);
        List<CreateGradeCommand> commands = List.of(gradeCommand1, gradeCommand2);

        SystemSetupStatus result = systemSetupService.createGrades(commands);

        assertNotNull(result);
        assertTrue(result.getSteps().get(SetupStep.CREATE_GRADES));
        verify(createGradeUseCase, times(1)).createGrade(gradeCommand1, schoolId);
        verify(createGradeUseCase, times(1)).createGrade(gradeCommand2, schoolId);
        verify(systemSetupRepository, times(1)).saveSystemStatus(any(SystemSetupStatus.class));
    }

    @Test
    void testCreateGrades_WhenStepIsInvalid() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        mockStatus.setCurrentStep(SetupStep.CREATE_SCHOOL);
        when(systemSetupRepository.getSystemSetupStatus(id)).thenReturn(Optional.of(mockStatus));

        CreateGradeCommand gradeCommand = mock(CreateGradeCommand.class);
        List<CreateGradeCommand> commands = List.of(gradeCommand);

        ForbiddenSetup exception = assertThrows(ForbiddenSetup.class, () -> systemSetupService.createGrades(commands));
        assertEquals("Cannot create grades at this step: CREATE_SCHOOL", exception.getMessage());
        verify(createGradeUseCase, never()).createGrade(any(), any());
    }

    @Test
    void testUpdateSystemStatus() {
        SystemSetupStatus mockStatus = new SystemSetupStatus();
        mockStatus.setId(id);
        when(systemSetupRepository.saveSystemStatus(mockStatus)).thenReturn(Optional.of(mockStatus));

        SystemSetupStatus result = systemSetupService.updateSystemStatus(mockStatus);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(systemSetupRepository, times(1)).saveSystemStatus(mockStatus);
    }
}
