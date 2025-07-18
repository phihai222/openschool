package com.openschool.infrastructure.adapter.in.rest.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}
