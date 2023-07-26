package com.budkevych.uniserviceapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ActionResponseDto {
    private String message;

    ActionResponseDto() {
    }
}
