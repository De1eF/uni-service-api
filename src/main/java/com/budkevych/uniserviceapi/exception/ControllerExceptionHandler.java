package com.budkevych.uniserviceapi.exception;

import com.budkevych.uniserviceapi.dto.response.ExceptionResponseDto;
import java.util.Arrays;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    ExceptionResponseDto handleRuntime(Throwable e) {
        return ExceptionResponseDto.builder()
                .throwable(e.getClass())
                .message(e.getMessage())
                .stackTrace(Arrays.toString(e.getStackTrace()))
                .build();
    }
}
