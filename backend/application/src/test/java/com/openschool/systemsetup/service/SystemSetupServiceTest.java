package com.openschool.systemsetup.service;

import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.school.port.in.CreateSchoolUseCase;
import com.openschool.school.port.in.command.CreateSchoolCommand;
import com.openschool.systemsetup.exeption.ForbiddenSetup;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @InjectMocks
    private SystemSetupService systemSetupService;

    private final UUID id = UUID.fromString("0c09669c-0e92-487b-8e33-df11ec6d8042");

    @BeforeEach
    void setUp() {
        try (var ignored = MockitoAnnotations.openMocks(this)) {
            systemSetupService = new SystemSetupService(systemSetupRepository, initRootUserUseCase, createSchoolUseCase);
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
