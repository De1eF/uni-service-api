package com.budkevych.uniserviceapi.dto.response;

import java.util.Set;
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
public class TeacherResponseDto {
    private Long id;
    private String name;
    private String surname;
    private Set<StudentResponseDto> students;
    private SubjectResponseDto subject;

    public TeacherResponseDto() {
    }
}
