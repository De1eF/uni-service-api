
package com.budkevych.uniserviceapi.dto.response;

import java.util.List;
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
public class StudentResponseDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private List<GradeResponseDto> grades;
    private List<TeacherResponseDto> teacher;
}
