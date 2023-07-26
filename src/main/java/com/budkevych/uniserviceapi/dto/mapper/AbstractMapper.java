package com.budkevych.uniserviceapi.dto.mapper;

public interface AbstractMapper<R,M,P> {
    P toDto(M model);

    M toModel(R requestDto);
}
