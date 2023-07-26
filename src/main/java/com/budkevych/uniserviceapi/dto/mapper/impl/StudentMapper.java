package com.budkevych.uniserviceapi.dto.mapper.impl;

import com.budkevych.uniserviceapi.dto.mapper.AbstractMapper;
import com.budkevych.uniserviceapi.dto.request.StudentRequestDto;
import com.budkevych.uniserviceapi.dto.response.StudentResponseDto;
import com.budkevych.uniserviceapi.model.Student;
import java.util.ArrayList;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper implements AbstractMapper<
        StudentRequestDto,
        Student,
        StudentResponseDto> {
    private final GradeMapper gradeMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public StudentResponseDto toDto(Student model) {
        return StudentResponseDto.builder()
                .id(model.getId())
                .age(model.getAge())
                .name(model.getName())
                .surname(model.getSurname())
                .grades(model.getGrades().stream().map(gradeMapper::toDto).toList())
                .teacher(model.getTeachers().stream().map(teacherMapper::toDto).toList())
                .build();
    }

    @Override
    public Student toModel(StudentRequestDto requestDto) {
        return Student.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .age(requestDto.getAge())
                .grades(new ArrayList<>())
                .teachers(new HashSet<>())
                .build();
    }
}
