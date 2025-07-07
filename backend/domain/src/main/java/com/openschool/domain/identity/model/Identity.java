package com.openschool.domain.identity.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Identity {
    private UUID id;
}