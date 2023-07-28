
package com.budkevych.uniserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class StudentRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Range(min = 1)
    private Integer age;
}
