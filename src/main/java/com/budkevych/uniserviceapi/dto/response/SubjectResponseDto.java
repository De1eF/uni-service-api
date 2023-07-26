package com.budkevych.uniserviceapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SubjectResponseDto {
    private Long id;
    private String name;

    public SubjectResponseDto() {
    }
}
