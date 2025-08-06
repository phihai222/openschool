package com.openschool.infrastructure.adapter.in.rest.department;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.department.port.in.*;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.dto.request.DepartmentRequestDto;
import com.openschool.infrastructure.adapter.in.rest.department.dto.response.DepartmentResponseDto;
import com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final GetListDepartmentUseCase getListDepartmentUseCase;
    private final GetDetailDepartmentUseCase getDetailDepartmentUseCase;
    private final DepartmentMapper departmentMapper;

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
        return ResponseEntity.ok(departmentMapper.convertToResponsePage(results));

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

}
