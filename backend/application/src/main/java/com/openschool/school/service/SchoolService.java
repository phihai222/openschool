package com.openschool.school.service;

import com.openschool.domain.school.School;
import com.openschool.school.port.in.CreateSchoolUseCase;
import com.openschool.school.port.in.command.CreateSchoolCommand;
import com.openschool.school.port.out.SchoolRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class SchoolService implements CreateSchoolUseCase {
    private final SchoolRepositoryPort schoolRepository;

    @Override
    public School create(CreateSchoolCommand command) {
        School school = School.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .type(command.getType())
                .address(command.getAddress())
                .phoneNumber(command.getPhoneNumber())
                .email(command.getEmail())
                .website(command.getWebsite())
                .defaultLanguage(command.getDefaultLanguage())
                .timezone(command.getTimezone())
                .build();

        return schoolRepository.create(school);
    }
}
