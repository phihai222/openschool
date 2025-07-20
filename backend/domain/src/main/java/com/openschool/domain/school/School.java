package com.openschool.domain.school;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class School {
    private UUID id;
    private String name;
    private SchoolType type;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String defaultLanguage;
    private String timezone;
}
