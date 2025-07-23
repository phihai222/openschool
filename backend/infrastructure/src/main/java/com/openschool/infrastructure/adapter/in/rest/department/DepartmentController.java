package com.openschool.infrastructure.adapter.in.rest.department;

import com.openschool.department.port.in.*;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.dto.DepartmentDto;
import com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper;
import com.openschool.infrastructure.adapter.out.persistence.department.repository.DepartmentRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final GetListDepartmentUseCase listDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final GetDetailDepartmentUseCase getDetailDepartmentUseCase;
    private final DepartmentRepositoryAdapter departmentRepositoryAdapter;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto createDepartmentDto) {
        Department department = createDepartmentUseCase.createDepartment(dtoToCreatedDepartmentCommand(createDepartmentDto));
        return ResponseEntity.ok(toDepartmentDto(departmentRepositoryAdapter.save(department)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable("id") Object id,
            @RequestBody DepartmentDto updateDepartmentDto) {
        updateDepartmentDto.setDepartmentId(UUID.fromString(id.toString()));
        Department department = updateDepartmentUseCase.updateDepartment(dtoToUpdatedDepartmentCommand(updateDepartmentDto));
        return ResponseEntity.ok(toDepartmentDto(departmentRepositoryAdapter.update(department)));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartmentList() {
        return ResponseEntity.ok(listDepartmentUseCase.getDepartmentList()
                .stream()
                .map(DepartmentMapper::toDepartmentDto)
                .collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentDetail(@PathVariable("id") Object id) {
        Department department = getDetailDepartmentUseCase.getDetailDepartment(id);
        return ResponseEntity.ok(toDepartmentDto(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Object id) {
        deleteDepartmentUseCase.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }


}
