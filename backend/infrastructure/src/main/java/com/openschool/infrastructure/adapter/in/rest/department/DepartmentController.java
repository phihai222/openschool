package com.openschool.infrastructure.adapter.in.rest.department;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.department.port.in.*;
import com.openschool.domain.department.Department;
import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.AssignEmployeeUseCase;
import com.openschool.employee.port.in.GetListEmployeeInDepartmentUseCase;
import com.openschool.employee.port.in.MoveEmployeeUseCase;
import com.openschool.employee.port.in.RemoveEmployeeUseCase;
import com.openschool.infrastructure.adapter.in.rest.department.dto.request.DepartmentRequestDto;
import com.openschool.infrastructure.adapter.in.rest.department.dto.response.DepartmentResponseDto;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.response.EmployeeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;
import static com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper.toEmployeeResponseDtoPageResult;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final GetListDepartmentUseCase getListDepartmentUseCase;
    private final GetDetailDepartmentUseCase getDetailDepartmentUseCase;
    private final AssignEmployeeUseCase assignEmployeeUseCase;
    private final GetListEmployeeInDepartmentUseCase getListEmployeeInDepartmentUseCase;
    private final MoveEmployeeUseCase moveEmployeeUseCase;
    private final RemoveEmployeeUseCase removeEmployeeUseCase;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto createDepartmentDto) {
        Department department = createDepartmentUseCase.createDepartment(dtoToCreatedDepartmentCommand(createDepartmentDto));
        return ResponseEntity.created(URI.create("/api/departments/" + department.getDepartmentId()))
                .body(toDepartmentResponseDto(department));

    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable("departmentId") UUID departmentId,
            @RequestBody DepartmentRequestDto updateDepartmentDto) {
        Department department = updateDepartmentUseCase.updateDepartment(dtoToUpdatedDepartmentCommand(departmentId, updateDepartmentDto));
        return ResponseEntity.ok(toDepartmentResponseDto(department));
    }

    @GetMapping
    public ResponseEntity<PageResult<DepartmentResponseDto>> getDepartmentList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .build();
        PageResult<Department> results = getListDepartmentUseCase.getDepartmentList(pageInfo);
        return ResponseEntity.ok(convertToResponsePage(results));

    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentDetail(@PathVariable("departmentId") UUID departmentId) {
        Department department = getDetailDepartmentUseCase.getDetailDepartment(departmentId);
        return ResponseEntity.ok(toDepartmentResponseDto(department));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        deleteDepartmentUseCase.deleteDepartment(departmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<PageResult<EmployeeResponseDto>> getListEmployeeInDepartment(
            @PathVariable("departmentId") UUID departmentId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .build();
        PageResult<Employee> results = getListEmployeeInDepartmentUseCase.getListEmployee(pageInfo, departmentId);
        return ResponseEntity.ok(toEmployeeResponseDtoPageResult(results));
    }

    @PutMapping("/{departmentId}/assign-employee")
    public ResponseEntity<?> assignEmployee(
            @PathVariable("departmentId") UUID departmentId,
            @RequestBody DepartmentRequestDto updateDepartmentDto) {
        assignEmployeeUseCase.assignEmployee(departmentId, updateDepartmentDto.getEmployeeIds());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{srcDepartmentId}/move-employee")
    public ResponseEntity<DepartmentResponseDto> moveEmployee(
            @PathVariable("srcDepartmentId") UUID srcDepartmentId,
            @RequestParam("desDepartmentId") UUID desDepartmentId,
            @RequestParam("employeeId") UUID employeeId) {
        moveEmployeeUseCase.moveEmployee(srcDepartmentId, desDepartmentId, employeeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{departmentId}/remove-employee")
    public ResponseEntity<DepartmentResponseDto> removeEmployee(
            @PathVariable("departmentId") UUID departmentId,
            @RequestParam("employeeId") UUID employeeId) {
        removeEmployeeUseCase.removeEmployee(departmentId, employeeId);
        return ResponseEntity.ok().build();
    }

}
