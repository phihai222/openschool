package com.openschool.infrastructure.adapter.out.persistence.employee.entity;

import com.openschool.domain.employee.EmployeeType;
import com.openschool.infrastructure.adapter.out.persistence.constant.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {

    @Id
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private EmployeeType employeeType;
}
