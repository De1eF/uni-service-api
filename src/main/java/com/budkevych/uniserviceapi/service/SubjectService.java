package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Subject;

public interface SubjectService {
    Subject add(Subject subject);

    Subject get(Long id);

    Subject update(Long id, Subject subject);

    void delete(Long id);
}
