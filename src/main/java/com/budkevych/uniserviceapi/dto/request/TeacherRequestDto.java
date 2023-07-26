package com.budkevych.uniserviceapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private Long subjectId;
}
