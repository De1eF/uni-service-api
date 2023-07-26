package com.budkevych.uniserviceapi.dto.mapper.impl;

import com.budkevych.uniserviceapi.dto.mapper.AbstractMapper;
import com.budkevych.uniserviceapi.dto.request.SubjectRequestDto;
import com.budkevych.uniserviceapi.dto.response.SubjectResponseDto;
import com.budkevych.uniserviceapi.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper implements AbstractMapper<
        SubjectRequestDto,
        Subject,
        SubjectResponseDto> {
    @Override
    public SubjectResponseDto toDto(Subject model) {
        return SubjectResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }

    @Override
    public Subject toModel(SubjectRequestDto requestDto) {
        return Subject.builder()
                .name(requestDto.getName())
                .build();
    }
}
