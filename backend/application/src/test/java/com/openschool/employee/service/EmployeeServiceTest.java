package com.openschool.employee.service;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.exception.EmployeeException;
import com.openschool.employee.port.in.command.CreatedEmployeeCommand;
import com.openschool.employee.port.in.command.UpdatedEmployeeCommand;
import com.openschool.employee.port.out.EmployeeRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.openschool.employee.exception.ExceptionMessage.EMPLOYEE_ALREADY_EXISTS;
import static com.openschool.employee.exception.ExceptionMessage.EMPLOYEE_CREATION_FAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    private EmployeeRepositoryPort employeeRepositoryPort;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepositoryPort = mock(EmployeeRepositoryPort.class);
        employeeService = new EmployeeService(employeeRepositoryPort);
    }

    @Test
    void shouldThrowExceptionIfEmployeeExists() {
        CreatedEmployeeCommand command = CreatedEmployeeCommand.builder()
                .firstName("John")
                .phoneNumber("0123456789")
                .email("john@example.com")
                .build();

        when(employeeRepositoryPort.findByPhoneNumberOrEmail(command.getPhoneNumber(), command.getEmail()))
                .thenReturn(Optional.of(new Employee()));

        EmployeeException exception = assertThrows(EmployeeException.class, () -> {
            employeeService.createEmployee(command);
        });

        assertEquals(EMPLOYEE_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCreationFails() {
        CreatedEmployeeCommand command = CreatedEmployeeCommand.builder()
                .firstName("Jane")
                .phoneNumber("0987654321")
                .email("jane@example.com")
                .build();

        when(employeeRepositoryPort.findByPhoneNumberOrEmail(command.getPhoneNumber(), command.getEmail()))
                .thenReturn(Optional.empty());

        when(employeeRepositoryPort.createEmployee(any()))
                .thenReturn(Optional.empty());

        EmployeeException exception = assertThrows(EmployeeException.class, () -> {
            employeeService.createEmployee(command);
        });

        assertEquals(EMPLOYEE_CREATION_FAIL, exception.getMessage());
    }

    @Test
    void shouldCreateEmployeeSuccessfully() {
        CreatedEmployeeCommand command = CreatedEmployeeCommand.builder()
                .firstName("Alice")
                .phoneNumber("0333555777")
                .email("alice@example.com")
                .build();

        Employee created = Employee.builder()
                .firstName("Alice")
                .phoneNumber("0333555777")
                .email("alice@example.com")
                .build();

        when(employeeRepositoryPort.findByPhoneNumberOrEmail(command.getPhoneNumber(), command.getEmail()))
                .thenReturn(Optional.empty());

        when(employeeRepositoryPort.createEmployee(any()))
                .thenReturn(Optional.of(created));

        Employee result = employeeService.createEmployee(command);

        assertNotNull(result);
        assertEquals("Alice", result.getFirstName());
        assertEquals("0333555777", result.getPhoneNumber());
    }

    @Test
    void updateEmployeeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        UpdatedEmployeeCommand command = UpdatedEmployeeCommand.builder()
                .employeeId(employeeId)
                .firstName("John")
                .lastName("Doe")
                .email("john-doe@gmail.com")
                .build();

        Employee currentEmployee = Employee.builder()
                .employeeId(employeeId)
                .firstName("John")
                .lastName("Doe")
                .email("john-doee@gmail.com")
                .build();

        Employee updatedEmployee = Employee.builder()
                .employeeId(employeeId)
                .firstName("John")
                .lastName("Doe")
                .email("john-doe@gmail.com")
                .build();
            

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(currentEmployee));
        when(employeeRepositoryPort.updateEmployee(any(Employee.class))).thenReturn(Optional.of(updatedEmployee));

        Employee result = employeeService.updateEmployee(command);

        assertNotNull(result);
        assertEquals("john-doe@gmail.com", result.getEmail());
    }

    @Test
    void updateEmployeeFailsWhenNotFound() {
        UUID employeeId = UUID.randomUUID();
        UpdatedEmployeeCommand command = new UpdatedEmployeeCommand();
        

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeException.class, () -> employeeService.updateEmployee(command));
    }

    @Test
    void deleteEmployeeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        Employee currentEmployee = new Employee();
        currentEmployee.setEmployeeId(employeeId);
        currentEmployee.setFirstName("John");
        currentEmployee.setLastName("Doe");

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(currentEmployee));
        when(employeeRepositoryPort.deleteEmployee(employeeId)).thenReturn(true);

        assertDoesNotThrow(() -> employeeService.deleteEmployee(employeeId));
    }

    @Test
    void deleteEmployeeFailsWhenNotFound() {
        UUID employeeId = UUID.randomUUID();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeException.class, () -> employeeService.deleteEmployee(employeeId));
    }

    @Test
    void getDetailEmployeeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getDetailEmployee(employeeId);

        assertNotNull(result);
        assertEquals("John Doe", result.getFirstName() + " " + result.getLastName());
    }

    @Test
    void getDetailEmployeeFailsWhenNotFound() {
        UUID employeeId = UUID.randomUUID();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeException.class, () -> employeeService.getDetailEmployee(employeeId));
    }

    @Test
    void getListEmployeeSuccessfully() {
        List<Employee> employees = List.of(
                Employee.builder()
                        .employeeId(UUID.randomUUID())
                        .firstName("John")
                        .lastName("Doe")
                        .build(),
                Employee.builder()
                        .employeeId(UUID.randomUUID())
                        .firstName("Jane")
                        .lastName("Smith")
                        .build()
                       );

        when(employeeRepositoryPort.getListEmployee()).thenReturn(employees);
        List<Employee> result = employeeService.getListEmployee();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}