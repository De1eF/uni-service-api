package com.budkevych.uniserviceapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class GradeRequestDto {
    @NotNull
    private Long subjectId;
    private Long studentId;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer grade;
}
