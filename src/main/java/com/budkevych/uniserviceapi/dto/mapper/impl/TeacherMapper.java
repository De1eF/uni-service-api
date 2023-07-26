package com.budkevych.uniserviceapi.dto.mapper.impl;

import com.budkevych.uniserviceapi.dto.mapper.AbstractMapper;
import com.budkevych.uniserviceapi.dto.request.TeacherRequestDto;
import com.budkevych.uniserviceapi.dto.response.StudentResponseDto;
import com.budkevych.uniserviceapi.dto.response.TeacherResponseDto;
import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.model.Teacher;
import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapper implements AbstractMapper<
        TeacherRequestDto,
        Teacher,
        TeacherResponseDto> {
    private final SubjectMapper subjectMapper;

    @Override
    public TeacherResponseDto toDto(Teacher model) {
        return TeacherResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .surname(model.getSurname())
                .subject(subjectMapper.toDto(model.getSubject()))
                .students(model.getStudents()
                        .stream()
                        .map(s -> StudentResponseDto
                                .builder()
                                .id(s.getId())
                                .name(s.getName())
                                .surname(s.getSurname())
                                .age(s.getAge())
                                .build()
                        ).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Teacher toModel(TeacherRequestDto requestDto) {
        return Teacher.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .students(new HashSet<>())
                .subject(Subject.builder().id(requestDto.getSubjectId()).build())
                .build();
    }
}
