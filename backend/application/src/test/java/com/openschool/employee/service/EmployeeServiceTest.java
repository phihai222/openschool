package com.openschool.employee.service;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.department.exception.DepartmentException;
import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.domain.department.Department;
import com.openschool.domain.employee.Employee;
import com.openschool.domain.employee.EmployeeType;
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
    private DepartmentRepositoryPort departmentRepositoryPort;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepositoryPort = mock(EmployeeRepositoryPort.class);
        departmentRepositoryPort = mock(DepartmentRepositoryPort.class);
        employeeService = new EmployeeService(employeeRepositoryPort, departmentRepositoryPort);

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
    void getListEmployeeReturnsPagedResults() {
        PageInfo pageInfo = new PageInfo(0, 2);
        List<Employee> employees = List.of(
                Employee.builder().employeeId(UUID.randomUUID()).firstName("John").build(),
                Employee.builder().employeeId(UUID.randomUUID()).firstName("Jane").build()
        );
        PageResult<Employee> pageResult = new PageResult<>(pageInfo.getPage(), pageInfo.getSize(), employees, 1L, 2L);

        when(employeeRepositoryPort.getListEmployee(pageInfo)).thenReturn(pageResult);

        PageResult<Employee> result = employeeService.getListEmployee(pageInfo);

        assertNotNull(result);
        assertEquals(2, result.getData().size());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void getListEmployeeReturnsEmptyWhenNoEmployees() {
        PageInfo pageInfo = new PageInfo(0, 10);
        PageResult<Employee> emptyResult = new PageResult<>();
        emptyResult.setData(List.of());
        emptyResult.setTotalElements(0L);

        when(employeeRepositoryPort.getListEmployee(pageInfo)).thenReturn(emptyResult);

        PageResult<Employee> result = employeeService.getListEmployee(pageInfo);

        assertNotNull(result);
        assertTrue(result.getData().isEmpty());
        assertEquals(0, result.getTotalElements());
    }

    @Test
    void assignEmployeeSuccessfullyWithMultipleEmployees() {
        UUID departmentId = UUID.randomUUID();
        UUID employeeId1 = UUID.randomUUID();
        UUID employeeId2 = UUID.randomUUID();
        Department department = Department.builder().departmentId(departmentId).build();
        Employee employee1 = Employee.builder().employeeId(employeeId1).department(departmentId).build();
        Employee employee2 = Employee.builder().employeeId(employeeId2).department(departmentId).build();

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.getDetailEmployee(employeeId1)).thenReturn(Optional.of(employee1));
        when(employeeRepositoryPort.getDetailEmployee(employeeId2)).thenReturn(Optional.of(employee2));
        when(employeeRepositoryPort.assignEmployeeToDepartment(departmentId, List.of(employee1, employee2))).thenReturn(true);

        assertDoesNotThrow(() -> employeeService.assignEmployee(departmentId, List.of(employeeId1, employeeId2)));
    }

    @Test
    void assignEmployeeThrowsIfNoEmployeesInDepartment() {
        UUID departmentId = UUID.randomUUID();
        UUID employeeId1 = UUID.randomUUID();
        UUID employeeId2 = UUID.randomUUID();
        Department department = Department.builder().departmentId(departmentId).build();
        Employee employee1 = Employee.builder().employeeId(employeeId1).department(UUID.randomUUID()).build();
        Employee employee2 = Employee.builder().employeeId(employeeId2).department(UUID.randomUUID()).build();

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.getDetailEmployee(employeeId1)).thenReturn(Optional.of(employee1));
        when(employeeRepositoryPort.getDetailEmployee(employeeId2)).thenReturn(Optional.of(employee2));

        assertThrows(EmployeeException.class, () -> employeeService.assignEmployee(departmentId, List.of(employeeId1, employeeId2)));
    }

    @Test
    void assignEmployeeThrowsIfAnyEmployeeNotFound() {
        UUID departmentId = UUID.randomUUID();
        UUID employeeId1 = UUID.randomUUID();
        UUID employeeId2 = UUID.randomUUID();
        Department department = Department.builder().departmentId(departmentId).build();
        Employee employee1 = Employee.builder().employeeId(employeeId1).department(departmentId).build();

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.getDetailEmployee(employeeId1)).thenReturn(Optional.of(employee1));
        when(employeeRepositoryPort.getDetailEmployee(employeeId2)).thenReturn(Optional.empty());

        assertThrows(EmployeeException.class, () -> employeeService.assignEmployee(departmentId, List.of(employeeId1, employeeId2)));
    }

    @Test
    void assignEmployeeThrowsIfAssignFailsWithMultipleEmployees() {
        UUID departmentId = UUID.randomUUID();
        UUID employeeId1 = UUID.randomUUID();
        UUID employeeId2 = UUID.randomUUID();
        Department department = Department.builder().departmentId(departmentId).build();
        Employee employee1 = Employee.builder().employeeId(employeeId1).department(departmentId).build();
        Employee employee2 = Employee.builder().employeeId(employeeId2).department(departmentId).build();

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.getDetailEmployee(employeeId1)).thenReturn(Optional.of(employee1));
        when(employeeRepositoryPort.getDetailEmployee(employeeId2)).thenReturn(Optional.of(employee2));
        when(employeeRepositoryPort.assignEmployeeToDepartment(departmentId, List.of(employee1, employee2))).thenReturn(false);

        assertThrows(EmployeeException.class, () -> employeeService.assignEmployee(departmentId, List.of(employeeId1, employeeId2)));
    }

    @Test
    void assignEmployeeThrowsIfDepartmentNotFoundWithMultipleEmployees() {
        UUID departmentId = UUID.randomUUID();
        UUID employeeId1 = UUID.randomUUID();
        UUID employeeId2 = UUID.randomUUID();

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(DepartmentException.class, () -> employeeService.assignEmployee(departmentId, List.of(employeeId1, employeeId2)));
    }

    @Test
    void getListEmployeeInDepartmentUseCaseReturnsPagedResults() {
        UUID departmentId = UUID.randomUUID();
        PageInfo pageInfo = new PageInfo(0, 2);
        Department department = Department.builder().departmentId(departmentId).build();
        List<Employee> employees = List.of(Employee.builder().employeeId(UUID.randomUUID()).build());
        PageResult<Employee> pageResult = new PageResult<>(pageInfo.getPage(), pageInfo.getSize(), employees, 1L, 1L);

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.getListEmployeeInDepartment(pageInfo, departmentId)).thenReturn(pageResult);

        PageResult<Employee> result = employeeService.getListEmployee(pageInfo, departmentId);

        assertNotNull(result);
        assertEquals(1, result.getData().size());
    }

    @Test
    void getListEmployeeInDepartmentUseCaseThrowsIfDepartmentNotFound() {
        UUID departmentId = UUID.randomUUID();
        PageInfo pageInfo = new PageInfo(0, 2);

        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(DepartmentException.class, () -> employeeService.getListEmployee(pageInfo, departmentId));
    }

    @Test
    void moveEmployeeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        UUID srcDeptId = UUID.randomUUID();
        UUID desDeptId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(srcDeptId).build();
        Department srcDept = Department.builder().departmentId(srcDeptId).build();
        Department desDept = Department.builder().departmentId(desDeptId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(srcDeptId)).thenReturn(Optional.of(srcDept));
        when(departmentRepositoryPort.findById(desDeptId)).thenReturn(Optional.of(desDept));
        when(employeeRepositoryPort.moveEmployee(desDeptId, employee)).thenReturn(true);

        assertDoesNotThrow(() -> employeeService.moveEmployee(srcDeptId, desDeptId, employeeId));
    }

    @Test
    void moveEmployeeThrowsIfEmployeeNotInSourceDepartment() {
        UUID employeeId = UUID.randomUUID();
        UUID srcDeptId = UUID.randomUUID();
        UUID desDeptId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(UUID.randomUUID()).build();
        Department srcDept = Department.builder().departmentId(srcDeptId).build();
        Department desDept = Department.builder().departmentId(desDeptId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(srcDeptId)).thenReturn(Optional.of(srcDept));
        when(departmentRepositoryPort.findById(desDeptId)).thenReturn(Optional.of(desDept));

        assertThrows(EmployeeException.class, () -> employeeService.moveEmployee(srcDeptId, desDeptId, employeeId));
    }

    @Test
    void moveEmployeeThrowsIfMoveFails() {
        UUID employeeId = UUID.randomUUID();
        UUID srcDeptId = UUID.randomUUID();
        UUID desDeptId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(srcDeptId).build();
        Department srcDept = Department.builder().departmentId(srcDeptId).build();
        Department desDept = Department.builder().departmentId(desDeptId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(srcDeptId)).thenReturn(Optional.of(srcDept));
        when(departmentRepositoryPort.findById(desDeptId)).thenReturn(Optional.of(desDept));
        when(employeeRepositoryPort.moveEmployee(desDeptId, employee)).thenReturn(false);

        assertThrows(EmployeeException.class, () -> employeeService.moveEmployee(srcDeptId, desDeptId, employeeId));
    }

    @Test
    void removeEmployeeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(departmentId).build();
        Department department = Department.builder().departmentId(departmentId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.removeEmployee(employee)).thenReturn(true);

        assertDoesNotThrow(() -> employeeService.removeEmployee(departmentId, employeeId));
    }

    @Test
    void removeEmployeeThrowsIfEmployeeNotInDepartment() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(UUID.randomUUID()).build();
        Department department = Department.builder().departmentId(departmentId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));

        assertThrows(EmployeeException.class, () -> employeeService.removeEmployee(departmentId, employeeId));
    }

    @Test
    void removeEmployeeThrowsIfRemoveFails() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        Employee employee = Employee.builder().employeeId(employeeId).department(departmentId).build();
        Department department = Department.builder().departmentId(departmentId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.removeEmployee(employee)).thenReturn(false);

        assertThrows(EmployeeException.class, () -> employeeService.removeEmployee(departmentId, employeeId));
    }

    @Test
    void setEmployeeTypeSuccessfully() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        EmployeeType employeeType = EmployeeType.PRINCIPAL;
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .department(departmentId)
                .employeeType(employeeType)
                .build();
        Department department = Department.builder()
                .departmentId(departmentId)
                .employeeIds(List.of(employeeId))
                .build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.setEmployee(employee, employeeType)).thenReturn(true);

        assertDoesNotThrow(() -> employeeService.setEmployeeType(departmentId, employeeId, employeeType));
    }

    @Test
    void setEmployeeTypeThrowsIfEmployeeNotInDepartment() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        EmployeeType employeeType = EmployeeType.PRINCIPAL;
        Employee employee = Employee.builder().employeeId(employeeId).department(UUID.randomUUID()).build();
        Department department = Department.builder().departmentId(departmentId).build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));

        assertThrows(EmployeeException.class, () -> employeeService.setEmployeeType(departmentId, employeeId, employeeType));
    }

    @Test
    void setEmployeeTypeThrowsIfSetFails() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        EmployeeType employeeType = EmployeeType.PRINCIPAL;
        Employee employee = Employee.builder().employeeId(employeeId).department(departmentId).build();
        Department department = Department.builder()
                .departmentId(departmentId)
                .employeeIds(List.of(employeeId))
                .build();

        // No homeroom teacher exists
        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepositoryPort.setEmployee(employee, employeeType)).thenReturn(false);

        assertThrows(EmployeeException.class, () -> employeeService.setEmployeeType(departmentId, employeeId, employeeType));
    }

    @Test
    void setEmployeeTypeThrowsIfHomeTeacherExists() {
        UUID employeeId = UUID.randomUUID();
        UUID departmentId = UUID.randomUUID();
        UUID existingHomeTeacherId = UUID.randomUUID();
        EmployeeType employeeType = EmployeeType.HOMEROOM_TEACHER;

        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .department(departmentId)
                .build();

        Employee homeTeacher = Employee.builder()
                .employeeId(existingHomeTeacherId)
                .department(departmentId)
                .employeeType(EmployeeType.HOMEROOM_TEACHER)
                .build();

        Department department = Department.builder()
                .departmentId(departmentId)
                .employeeIds(List.of(employeeId, existingHomeTeacherId))
                .build();

        when(employeeRepositoryPort.getDetailEmployee(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepositoryPort.getDetailEmployee(existingHomeTeacherId)).thenReturn(Optional.of(homeTeacher));
        when(departmentRepositoryPort.findById(departmentId)).thenReturn(Optional.of(department));

        assertThrows(EmployeeException.class, () -> employeeService.setEmployeeType(departmentId, employeeId, employeeType));
    }
}