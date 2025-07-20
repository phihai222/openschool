package com.openschool.school.port.in.command;

import com.openschool.domain.school.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateSchoolCommand {
    private String name;
    private SchoolType type;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String defaultLanguage;
    private String timezone;
}
