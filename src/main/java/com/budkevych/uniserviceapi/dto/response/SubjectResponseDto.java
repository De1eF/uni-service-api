package com.budkevych.uniserviceapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SubjectResponseDto {
    private Long id;
    private String name;
}
