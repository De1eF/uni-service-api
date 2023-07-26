package com.budkevych.uniserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequestDto {
    @NotBlank
    private String name;
}
