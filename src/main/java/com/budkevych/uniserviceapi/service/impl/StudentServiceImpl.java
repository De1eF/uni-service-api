package com.budkevych.uniserviceapi.service.impl;

import com.budkevych.uniserviceapi.exception.NotFoundException;
import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.repository.StudentRepository;
import com.budkevych.uniserviceapi.service.GradeService;
import com.budkevych.uniserviceapi.service.StudentService;
import com.budkevych.uniserviceapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GradeService gradeService;
    private final TeacherService teacherService;

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No student found for id " + id));
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
    public Student addGrades(Long studentId, Grade grade) {
        Student student = get(studentId);
        grade.getStudent().setId(studentId);
        gradeService.add(grade);
        student.getGrades().add(grade);
        return update(studentId, student);
    }

    @Override
    public Student addTeacher(Long studentId, Long teacherId) {
        Teacher teacher = teacherService.get(teacherId);
        Student student = get(studentId);
        student.getTeachers().add(teacher);
        return update(studentId, student);
    }
}
