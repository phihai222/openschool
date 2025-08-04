package com.openschool.academic.service;

import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.academic.port.in.command.CreateSemesterCommand;
import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.domain.academic.AcademicYear;
import com.openschool.domain.academic.AcademicYearStatus;
import com.openschool.domain.school.School;
import com.openschool.school.port.out.SchoolRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcademicYearServiceTest {
    private AcademicYearRepositoryPort academicYearRepository;
    private SchoolRepositoryPort schoolRepository;
    private AcademicYearService academicYearService;

    @BeforeEach
    void setUp() {
        academicYearRepository = mock(AcademicYearRepositoryPort.class);
        schoolRepository = mock(SchoolRepositoryPort.class);
        academicYearService = new AcademicYearService(academicYearRepository, schoolRepository);
    }

    @Test
    void create_ShouldReturnAcademicYearWithGeneratedIds() {
        // Arrange
        CreateSemesterCommand semester1 = new CreateSemesterCommand();
        semester1.setName("Semester 1");
        semester1.setStartDate(LocalDate.of(2025, 1, 1));
        semester1.setEndDate(LocalDate.of(2025, 6, 1));
        CreateSemesterCommand semester2 = new CreateSemesterCommand();
        semester2.setName("Semester 2");
        semester2.setStartDate(LocalDate.of(2025, 7, 1));
        semester2.setEndDate(LocalDate.of(2025, 12, 1));
        List<CreateSemesterCommand> semesters = Arrays.asList(semester1, semester2);

        UUID schoolId = UUID.randomUUID();
        School mockSchool = mock(School.class);
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(mockSchool));
        CreateAcademicYearCommand command = CreateAcademicYearCommand.builder()
                .schoolId(schoolId)
                .code("2025-2026")
                .name("Academic Year 2025-2026")
                .startDate(LocalDate.of(2025, 1, 1))
                .endDate(LocalDate.of(2025, 12, 31))
                .status(AcademicYearStatus.ACTIVE)
                .semesters(semesters)
                .build();

        when(academicYearRepository.create(any(AcademicYear.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        AcademicYear result = academicYearService.create(command);

        // Assert
        assertNotNull(result.getId());
        assertEquals(command.getCode(), result.getCode());
        assertEquals(command.getName(), result.getName());
        assertEquals(command.getStartDate(), result.getStartDate());
        assertEquals(command.getEndDate(), result.getEndDate());
        assertEquals(command.getStatus(), result.getStatus());
        assertEquals(2, result.getSemesters().size());
        result.getSemesters().forEach(s -> assertNotNull(s.getId()));
        verify(academicYearRepository, times(1)).create(any(AcademicYear.class));
    }
}
