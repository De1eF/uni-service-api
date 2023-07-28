package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void delete(Long id);

    Student addGrades(Long studentId, Grade grade);

    Student removeGrades(Long studentId, Long gradeId);

    Student addTeacher(Long studentId, Long teacherId);

    Student removeTeacher(Long studentId, Long teacherId);
}
