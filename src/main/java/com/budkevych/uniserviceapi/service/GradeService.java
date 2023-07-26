package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Grade;

public interface GradeService {
    Grade add(Grade grade);

    Grade get(Long id);

    Grade update(Long id, Grade grade);

    void delete(Long id);
}
