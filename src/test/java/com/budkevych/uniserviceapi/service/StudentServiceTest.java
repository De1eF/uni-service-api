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
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void mocks() {
        Student studentFromDb = getStudent();
        studentFromDb.setId(1L);

        Subject subject = Subject.builder()
                .id(1L)
                .name("History")
                .build();

        Teacher teacherFromDb = Teacher.builder()
                .id(1L)
                .students(new HashSet<>())
                .subject(subject)
                .name("c")
                .surname("d")
                .build();

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(studentFromDb));
        Mockito.when(studentRepository.save(studentFromDb)).thenReturn(studentFromDb);
        Mockito.when(teacherService.get(1L)).thenReturn(teacherFromDb);
    }

    @Test
    void addGradesOk() {
        Student student = Student.builder()
                .id(1L)
                .build();
        Subject subject = Subject.builder()
                .id(1L)
                .build();
        Grade grade = Grade.builder()
                .subject(subject)
                .student(student)
                .grade(5)
                .build();

        Student studentWithGrades = studentService.addGrades(student.getId(), grade);

        Assertions.assertEquals(studentWithGrades.getGrades(), List.of(grade));
    }

    @Test
    void addTeacherOk() {
        Student studentWithTeacher = studentService.addTeacher(1L, 1L);

        Subject subject = Subject.builder()
                .id(1L)
                .name("History")
                .build();

        Teacher expected = Teacher.builder()
                .id(1L)
                .students(new HashSet<>())
                .subject(subject)
                .name("c")
                .surname("d")
                .build();

        Assertions.assertEquals(studentWithTeacher.getTeachers(), Set.of(expected));
    }

    @Test
    void addOk() {
        Student student = getStudent();
        Student expected = getStudent();
        expected.setId(1L);

        Student actual = studentService.add(student);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOk() {
        Student expected = getStudent();
        expected.setId(1L);

        Student actual = studentService.get(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateOk() {
        Student student = getStudent();
        Student expected = getStudent();
        expected.setId(1L);

        Student actual = studentService.update(1L, student);
        Assertions.assertEquals(expected, actual);
    }

    private Student getStudent() {
        return Student.builder()
                .id(1L)
                .grades(new ArrayList<>())
                .teachers(new HashSet<>())
                .name("a")
                .surname("b")
                .age(15)
                .build();
    }
}