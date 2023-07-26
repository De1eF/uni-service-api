package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Teacher;

public interface TeacherService {
    Teacher add(Teacher teacher);

    Teacher get(Long id);

    Teacher update(Long id, Teacher teacher);

    void delete(Long id);
}
