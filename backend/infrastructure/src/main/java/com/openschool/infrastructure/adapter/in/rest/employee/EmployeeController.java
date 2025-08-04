package com.openschool.infrastructure.adapter.in.rest.employee;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.*;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.request.EmployeeRequestDto;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.response.EmployeeResponseDto;
import com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper.dtoToCreatedEmployeeCommand;
import static com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper.dtoToUpdatedEmployeeCommand;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final GetListEmployeeUseCase getListEmployeeUseCase;
    private final GetDetailEmployeeUseCase getDetailEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getListEmployee() {
        List<Employee> employees = getListEmployeeUseCase.getListEmployee();
        List<EmployeeResponseDto> responseDTOs = employees.stream()
                .map(EmployeeMapper::toEmployeeResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getDetailEmployee(
            @PathVariable ("employeeId") UUID employeeId) {
        Employee employee = getDetailEmployeeUseCase.getDetailEmployee(employeeId);
        EmployeeResponseDto responseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto dto) {
        Employee employee = createEmployeeUseCase.createEmployee(dtoToCreatedEmployeeCommand(dto));
        EmployeeResponseDto responseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return ResponseEntity.created(URI.create("/api/employees/" + employee.getEmployeeId()))
                .body(responseDto);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable ("employeeId") UUID employeeId,
            @RequestBody EmployeeRequestDto dto) {
        Employee employee = updateEmployeeUseCase.updateEmployee(dtoToUpdatedEmployeeCommand(employeeId, dto));
        EmployeeResponseDto responseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable UUID employeeId) {
        deleteEmployeeUseCase.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

}
