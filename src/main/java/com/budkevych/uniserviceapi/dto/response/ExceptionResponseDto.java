package com.budkevych.uniserviceapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponseDto {
    private Class<?> throwable;
    private String message;
    private String stackTrace;
}
