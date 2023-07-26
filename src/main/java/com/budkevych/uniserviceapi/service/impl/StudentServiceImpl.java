package com.budkevych.uniserviceapi.service.impl;

import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.repository.StudentRepository;
import com.budkevych.uniserviceapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found for id " + id));
    }

    @Override
    public Student update(Long id, Student student) {
        get(id);
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void addGrades(Long studentId, Grade grade) {
        Student student = get(studentId);
        student.getGrades().add(grade);
        studentRepository.save(student);
    }
}
