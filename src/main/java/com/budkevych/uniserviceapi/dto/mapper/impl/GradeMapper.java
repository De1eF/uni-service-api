package com.budkevych.uniserviceapi.dto.mapper.impl;

import com.budkevych.uniserviceapi.dto.mapper.AbstractMapper;
import com.budkevych.uniserviceapi.dto.request.GradeRequestDto;
import com.budkevych.uniserviceapi.dto.response.GradeResponseDto;
import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Subject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GradeMapper implements AbstractMapper<GradeRequestDto, Grade, GradeResponseDto> {
    private final SubjectMapper subjectMapper;

    @Override
    public GradeResponseDto toDto(Grade model) {
        return GradeResponseDto.builder()
                .id(model.getId())
                .subject(subjectMapper.toDto(model.getSubject()))
                .grade(model.getGrade())
                .build();
    }

    @Override
    public Grade toModel(GradeRequestDto requestDto) {
        Student student = new Student();
        student.setId(requestDto.getStudentId());
        return Grade.builder()
                .student(student)
                .subject(Subject.builder()
                        .id(requestDto.getSubjectId())
                        .build())
                .grade(requestDto.getGrade())
                .build();
    }
}
