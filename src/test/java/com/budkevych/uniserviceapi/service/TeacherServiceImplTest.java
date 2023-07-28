package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.repository.TeacherRepository;
import com.budkevych.uniserviceapi.service.impl.TeacherServiceImpl;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherServiceImplTest {
    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Mock
    private TeacherRepository teacherRepository;

    @Test
    void addOk() {
        Teacher grade = getTeacher();
        Teacher fromDB = getTeacher();
        fromDB.setId(1L);
        Teacher expected = getTeacher();
        expected.setId(1L);

        Mockito.when(teacherRepository.save(grade)).thenReturn(fromDB);

        Teacher actual = teacherService.add(grade);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOk() {
        Teacher fromDB = getTeacher();
        fromDB.setId(1L);
        Teacher expected = getTeacher();
        expected.setId(1L);

        Mockito.when(teacherRepository.findById(fromDB.getId()))
                .thenReturn(Optional.of(fromDB));

        Teacher actual = teacherService.get(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateOk() {
        Teacher grade = getTeacher();
        Teacher fromDB = getTeacher();
        fromDB.setId(1L);
        Teacher expected = getTeacher();
        expected.setId(1L);

        Mockito.when(teacherRepository.save(grade)).thenReturn(fromDB);
        Mockito.when(teacherRepository.findById(1L))
                .thenReturn(Optional.of(fromDB));

        Teacher actual = teacherService.update(1L, grade);
        Assertions.assertEquals(expected, actual);
    }

    private Teacher getTeacher() {
        return Teacher.builder()
                .name("a")
                .surname("b")
                .subject(new Subject(1L, "Math"))
                .students(
                        Set.of(
                                Student.builder()
                                        .id(1L)
                                        .build()
                        )
                )
                .build();
    }
}