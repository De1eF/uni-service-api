package com.budkevych.uniserviceapi.controller;

import com.budkevych.uniserviceapi.dto.mapper.impl.TeacherMapper;
import com.budkevych.uniserviceapi.dto.request.TeacherRequestDto;
import com.budkevych.uniserviceapi.dto.response.ActionResponseDto;
import com.budkevych.uniserviceapi.dto.response.TeacherResponseDto;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping
    @Operation(summary = "add new teacher")
    public TeacherResponseDto add(@RequestBody @Valid TeacherRequestDto requestDto) {
        return teacherMapper.toDto(teacherService.add(teacherMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a teacher")
    public TeacherResponseDto get(@PathVariable Long id) {
        return teacherMapper.toDto(teacherService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a teacher")
    public TeacherResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid TeacherRequestDto requestDto) {
        return teacherMapper.toDto(teacherService.update(id, teacherMapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a teacher")
    public ActionResponseDto delete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ActionResponseDto("Teacher deleted on id %s".formatted(id));
    }
}
