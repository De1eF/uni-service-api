package com.budkevych.uniserviceapi.controller;

import com.budkevych.uniserviceapi.dto.mapper.impl.GradeMapper;
import com.budkevych.uniserviceapi.dto.mapper.impl.StudentMapper;
import com.budkevych.uniserviceapi.dto.request.GradeRequestDto;
import com.budkevych.uniserviceapi.dto.request.StudentRequestDto;
import com.budkevych.uniserviceapi.dto.response.ActionResponseDto;
import com.budkevych.uniserviceapi.dto.response.StudentResponseDto;
import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.service.GradeService;
import com.budkevych.uniserviceapi.service.StudentService;
import com.budkevych.uniserviceapi.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;

    @PostMapping
    @Operation(summary = "add a new student to db")
    public StudentResponseDto add(@RequestBody @Valid StudentRequestDto requestDto) {
        return studentMapper.toDto(studentService.add(studentMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a student from db")
    public StudentResponseDto get(@PathVariable Long id) {
        return studentMapper.toDto(studentService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a student in db")
    public StudentResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid StudentRequestDto requestDto) {
        return studentMapper.toDto(studentService.update(id, studentMapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a student from db")
    public ActionResponseDto delete(@PathVariable Long id) {
        studentService.delete(id);
        return ActionResponseDto.builder()
                .message("Student deleted on id %s".formatted(id))
                .build();
    }

    @PutMapping("/{id}/add-grade")
    @Operation(summary = "add a grade to a student")
    public StudentResponseDto addGrade(@PathVariable Long id,
                                       @RequestBody @Valid GradeRequestDto gradeRequestDto) {
        Grade grade = gradeMapper.toModel(gradeRequestDto);
        return studentMapper.toDto(studentService.addGrades(id, grade));
    }

    @PutMapping("/{id}/assign-teacher/{teacherId}")
    @Operation(summary = "assign a teacher to the student")
    public StudentResponseDto addTeacher(@PathVariable Long id,
                                         @PathVariable Long teacherId) {
        return studentMapper.toDto(studentService.addTeacher(id, teacherId));
    }
}
