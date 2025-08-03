package com.openschool.infrastructure.adapter.out.persistence.school.entity;

import com.openschool.domain.school.School;
import com.openschool.domain.school.SchoolType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class SchoolEntity {
    @Id
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private SchoolType type;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String defaultLanguage;
    private String timezone;

    public static SchoolEntity referenceOnly(UUID id) {
        SchoolEntity entity = new SchoolEntity();
        entity.setId(id);
        return entity;
    }

    public School toDomain() {
        return School.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .website(this.website)
                .defaultLanguage(this.defaultLanguage)
                .timezone(this.timezone)
                .build();
    }
}
