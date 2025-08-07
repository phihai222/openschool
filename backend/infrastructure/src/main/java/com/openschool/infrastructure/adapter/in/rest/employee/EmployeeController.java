package com.openschool.infrastructure.adapter.in.rest.employee;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.employee.Employee;
import com.openschool.domain.employee.EmployeeType;
import com.openschool.employee.port.in.*;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.request.EmployeeRequestDto;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.response.EmployeeResponseDto;
import com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final GetListEmployeeUseCase getListEmployeeUseCase;
    private final GetDetailEmployeeUseCase getDetailEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final SetEmployeeTypeUseCase setEmployeeTypeUseCase;

    @GetMapping
    public ResponseEntity<PageResult<EmployeeResponseDto>> getListEmployee(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .build();
        PageResult<Employee> employees = getListEmployeeUseCase.getListEmployee(pageInfo);
        return ResponseEntity.ok(toEmployeeResponseDtoPageResult(employees));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getDetailEmployee(
            @PathVariable("employeeId") UUID employeeId) {
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
            @PathVariable("employeeId") UUID employeeId,
            @RequestBody EmployeeRequestDto dto) {
        Employee employee = updateEmployeeUseCase.updateEmployee(dtoToUpdatedEmployeeCommand(employeeId, dto));
        EmployeeResponseDto responseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable UUID employeeId) {
        deleteEmployeeUseCase.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{employeeId}/set-type")
    public ResponseEntity<EmployeeResponseDto> setEmployeeType(@PathVariable UUID employeeId,
                                                               @RequestParam("departmentId") UUID departmentId,
                                                               @RequestParam("type") EmployeeType employeeType) {
        setEmployeeTypeUseCase.setEmployeeType(departmentId, employeeId, employeeType);
        return ResponseEntity.ok().build();
    }

}
