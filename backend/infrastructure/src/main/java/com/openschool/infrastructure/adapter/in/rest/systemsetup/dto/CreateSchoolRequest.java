package com.openschool.infrastructure.adapter.in.rest.systemsetup.dto;

import com.openschool.domain.school.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSchoolRequest {
    private String name;
    private SchoolType type;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String defaultLanguage;
    private String timezone;
}
