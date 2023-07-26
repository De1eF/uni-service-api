package com.budkevych.uniserviceapi.controller;

import com.budkevych.uniserviceapi.dto.mapper.impl.SubjectMapper;
import com.budkevych.uniserviceapi.dto.request.SubjectRequestDto;
import com.budkevych.uniserviceapi.dto.response.ActionResponseDto;
import com.budkevych.uniserviceapi.dto.response.SubjectResponseDto;
import com.budkevych.uniserviceapi.service.SubjectService;
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
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @PostMapping
    @Operation(summary = "add new subject")
    public SubjectResponseDto add(@RequestBody @Valid SubjectRequestDto requestDto) {
        return subjectMapper.toDto(subjectService.add(subjectMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a subject")
    public SubjectResponseDto get(@PathVariable Long id) {
        return subjectMapper.toDto(subjectService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a subject")
    public SubjectResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid SubjectRequestDto requestDto) {
        return subjectMapper.toDto(subjectService.update(id, subjectMapper.toModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a subject")
    public ActionResponseDto delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ActionResponseDto.builder()
                .message("Student deleted on id %s".formatted(id))
                .build();
    }
}
