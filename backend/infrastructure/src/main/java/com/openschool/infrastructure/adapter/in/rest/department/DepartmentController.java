package com.openschool.infrastructure.adapter.in.rest.department;

import com.openschool.department.service.DepartmentService;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.dto.request.DepartmentRequestDto;
import com.openschool.infrastructure.adapter.in.rest.department.dto.response.DepartmentResponseDto;
import com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentRequestDto createDepartmentDto) {
        Department department = departmentService.createDepartment(dtoToCreatedDepartmentCommand(createDepartmentDto));
        return ResponseEntity.ok(toDepartmentResponseDto(department));

    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable("departmentId") UUID departmentId,
            @RequestBody DepartmentRequestDto updateDepartmentDto) {
        Department department = departmentService.updateDepartment(dtoToUpdatedDepartmentCommand(departmentId, updateDepartmentDto));
        return ResponseEntity.ok(toDepartmentResponseDto(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getDepartmentList() {
        return ResponseEntity.ok(departmentService.getDepartmentList()
                .stream()
                .map(DepartmentMapper::toDepartmentResponseDto)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentDetail(@PathVariable("departmentId") UUID departmentId) {
        Department department = departmentService.getDetailDepartment(departmentId);
        return ResponseEntity.ok(toDepartmentResponseDto(department));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok().build();
    }


}
