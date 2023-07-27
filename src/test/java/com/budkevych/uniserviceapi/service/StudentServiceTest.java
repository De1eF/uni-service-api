package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.repository.StudentRepository;
import com.budkevych.uniserviceapi.service.impl.StudentServiceImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private GradeService gradeService;
    @Mock
    private TeacherService teacherService;

    @Test
    void addGradesOk() {
        Student student = Student.builder()
                .id(1L)
                .build();
        Student studentFromDb = Student.builder()
                .id(1L)
                .grades(new ArrayList<>())
                .teachers(new HashSet<>())
                .name("a")
                .surname("b")
                .age(15)
                .build();
        Subject subject = Subject.builder()
                .id(1L)
                .build();
        Grade grade = Grade.builder()
                .subject(subject)
                .student(student)
                .grade(5)
                .build();

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(studentFromDb));
        Mockito.when(studentRepository.save(studentFromDb)).thenReturn(studentFromDb);

        Student studentWithGrades = studentService.addGrades(student.getId(), grade);

        Assertions.assertEquals(studentWithGrades.getGrades(), List.of(grade));
    }

    @Test
    void addTeacherOk() {
        Subject subject = Subject.builder()
                .id(1L)
                .name("History")
                .build();
        Student studentFromDb = Student.builder()
                .id(1L)
                .grades(new ArrayList<>())
                .teachers(new HashSet<>())
                .name("a")
                .surname("b")
                .age(15)
                .build();
        Teacher teacherFromDb = Teacher.builder()
                .id(1L)
                .students(new HashSet<>())
                .subject(subject)
                .name("c")
                .surname("d")
                .build();

        Mockito.when(teacherService.get(1L)).thenReturn(teacherFromDb);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(studentFromDb));
        Mockito.when(studentRepository.save(studentFromDb)).thenReturn(studentFromDb);

        Student studentWithTeacher = studentService.addTeacher(1L, 1L);

        Assertions.assertEquals(studentWithTeacher.getTeachers(), Set.of(teacherFromDb));
    }
}