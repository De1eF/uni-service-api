package com.budkevych.uniserviceapi.intgration;

import com.budkevych.uniserviceapi.exception.NotFoundException;
import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.repository.GradeRepository;
import com.budkevych.uniserviceapi.repository.StudentRepository;
import com.budkevych.uniserviceapi.repository.SubjectRepository;
import com.budkevych.uniserviceapi.repository.TeacherRepository;
import com.budkevych.uniserviceapi.service.StudentService;
import com.budkevych.uniserviceapi.service.SubjectService;
import com.budkevych.uniserviceapi.service.TeacherService;
import com.budkevych.uniserviceapi.service.impl.GradeServiceImpl;
import com.budkevych.uniserviceapi.service.impl.StudentServiceImpl;
import com.budkevych.uniserviceapi.service.impl.SubjectServiceImpl;
import com.budkevych.uniserviceapi.service.impl.TeacherServiceImpl;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentServiceRepositoryIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    private StudentService studentService;

    private TeacherService teacherService;

    @PostConstruct
    public void getService() {
        SubjectService subjectService =
                new SubjectServiceImpl(subjectRepository);
        subjectService.add(Subject.builder().name("").build());

        teacherService =
                new TeacherServiceImpl(teacherRepository);

        studentService = new StudentServiceImpl(
                studentRepository,
                new GradeServiceImpl(
                        gradeRepository,
                        subjectService
                ),
                teacherService
        );
    }

    @Test
    public void studentSaveGetOk() {
        Student student = getStudent();
        student = studentService.add(student);

        Student actual = studentService.get(student.getId());
        Assertions.assertNotEquals(null, actual);
    }

    @Test
    public void studentUpdateOk() {
        Student student = getStudent();
        student = studentService.add(student);

        student.setName("g");
        studentService.update(student.getId(), student);

        Student actual = studentService.get(student.getId());
        Assertions.assertEquals("g", actual.getName());
    }

    @Test
    public void studentUpdateNonExistentException() {
        Student student = getStudent();
        studentService.add(student);

        Assertions.assertThrows(NotFoundException.class,
                () -> studentService.update(99L, student));
    }

    @Test
    public void studentAddGradeGetOk() {
        Student student = getStudent();
        student = studentService.add(student);

        Grade grade = Grade.builder()
                .student(student)
                .grade(5)
                .subject(Subject.builder().id(1L).build())
                .build();
        studentService.addGrades(student.getId(), grade);

        Student actual = studentService.get(student.getId());
        Assertions.assertEquals(List.of(grade), actual.getGrades());
    }

    @Test
    public void studentAddGradeNonExistentSubjectGetOk() {
        Student student = getStudent();
        student = studentService.add(student);

        Grade grade = Grade.builder()
                .student(student)
                .grade(5)
                .subject(new Subject(99L, ""))
                .build();

        Long studentId = student.getId();
        Assertions.assertThrows(NotFoundException.class,
                () -> studentService.addGrades(studentId, grade));
    }

    @Test
    public void studentAddTeacherGetOk() {
        Student student = getStudent();
        student = studentService.add(student);

        Teacher teacher = Teacher.builder()
                .name("a")
                .surname("b")
                .students(new HashSet<>())
                .subject(Subject.builder().id(1L).build())
                .build();
        teacher = teacherService.add(teacher);

        studentService.addTeacher(student.getId(), teacher.getId());

        Student actual = studentService.get(student.getId());
        Assertions.assertEquals(Set.of(teacher), actual.getTeachers());
    }

    @Test
    public void studentAddNonExistentTeacherException() {
        Student student = getStudent();
        student = studentService.add(student);

        Long studentId = student.getId();
        Assertions.assertThrows(NotFoundException.class,
                () -> studentService.addTeacher(studentId, 99L));
    }

    private Student getStudent() {
        return Student.builder()
                .name("a")
                .surname("b")
                .age(15)
                .teachers(new HashSet<>())
                .grades(new ArrayList<>())
                .build();
    }
}