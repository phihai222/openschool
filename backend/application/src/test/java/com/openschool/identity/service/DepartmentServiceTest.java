package com.openschool.identity.service;

import com.openschool.department.exception.DepartmentException;
import com.openschool.department.exception.ExceptionMessage;
import com.openschool.department.port.in.command.CreatedDepartmentCommand;
import com.openschool.department.port.in.command.UpdateDepartmentCommand;
import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.department.service.DepartmentService;
import com.openschool.domain.department.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    private DepartmentRepositoryPort departmentRepositoryPort;
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        departmentRepositoryPort = mock(DepartmentRepositoryPort.class);
        departmentService = new DepartmentService(departmentRepositoryPort);
    }

    @Test
    void createDepartmentThrowsWhenDepartmentExists() {
        CreatedDepartmentCommand command = new CreatedDepartmentCommand();
        command.setDepartmentName("HR");
        when(departmentRepositoryPort.findByDepartmentName("HR"))
                .thenReturn(Optional.of(new Department()));

        DepartmentException ex = assertThrows(DepartmentException.class,
                () -> departmentService.createDepartment(command));
        assertEquals(ExceptionMessage.DEPARTMENT_ALREADY_EXISTS, ex.getMessage());
    }

    @Test
    void createDepartmentSavesWhenNotExists() {
        CreatedDepartmentCommand command = new CreatedDepartmentCommand();
        command.setDepartmentName("Finance");
        Department department = new Department();
        when(departmentRepositoryPort.findByDepartmentName("Finance"))
                .thenReturn(Optional.empty());
        when(departmentRepositoryPort.save(any(Department.class)))
                .thenReturn(department);

        Department result = departmentService.createDepartment(command);
        assertNotNull(result);
        verify(departmentRepositoryPort).save(any(Department.class));
    }

    @Test
    void updateDepartmentThrowsWhenNotFound() {
        UpdateDepartmentCommand command = new UpdateDepartmentCommand();
        command.setDepartmentId(UUID.randomUUID());
        command.setDepartmentName("Marketing");
        when(departmentRepositoryPort.findById(command.getDepartmentId()))
                .thenReturn(Optional.empty());

        DepartmentException ex = assertThrows(DepartmentException.class,
                () -> departmentService.updateDepartment(command));
        assertEquals(ExceptionMessage.DEPARTMENT_NOT_FOUND, ex.getMessage());
    }

    @Test
    void getDepartmentListReturnsAll() {
        List<Department> departments = List.of(new Department(), new Department());
        when(departmentRepositoryPort.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getDepartmentList();
        assertEquals(2, result.size());
    }

    @Test
    void getDetailDepartmentThrowsWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(departmentRepositoryPort.findById(id)).thenReturn(Optional.empty());

        DepartmentException ex = assertThrows(DepartmentException.class,
                () -> departmentService.getDetailDepartment(id));
        assertEquals(ExceptionMessage.DEPARTMENT_NOT_FOUND, ex.getMessage());
    }

    @Test
    void deleteDepartmentThrowsWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(departmentRepositoryPort.findById(id)).thenReturn(Optional.empty());

        DepartmentException ex = assertThrows(DepartmentException.class,
                () -> departmentService.deleteDepartment(id));
        assertEquals(ExceptionMessage.DEPARTMENT_NOT_FOUND, ex.getMessage());
    }

    @Test
    void deleteDepartmentThrowsWhenDeleteFails() {
        UUID id = UUID.randomUUID();
        Department department = new Department();
        when(departmentRepositoryPort.findById(id)).thenReturn(Optional.of(department));
        when(departmentRepositoryPort.delete(department)).thenReturn(false);

        DepartmentException ex = assertThrows(DepartmentException.class,
                () -> departmentService.deleteDepartment(id));
        assertEquals(ExceptionMessage.DEPARTMENT_DELETION_FAIL, ex.getMessage());
    }

    @Test
    void deleteDepartmentSucceeds() {
        UUID id = UUID.randomUUID();
        Department department = new Department();
        when(departmentRepositoryPort.findById(id)).thenReturn(Optional.of(department));
        when(departmentRepositoryPort.delete(department)).thenReturn(true);

        assertDoesNotThrow(() -> departmentService.deleteDepartment(id));
        verify(departmentRepositoryPort).delete(department);
    }
}