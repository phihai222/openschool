package com.openschool.infrastructure.adapter.out.persistence.department.entity;

import com.openschool.infrastructure.adapter.out.persistence.constant.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity extends BaseEntity {
    @Id
    @Column(name = "department_id")
    private UUID departmentId;

    private String departmentName;
    private String description;
    private String departmentHead;
    private String departmentDeputy;
    private String departmentCode;
    private String departmentEmail;
    private String departmentPhone;
}
