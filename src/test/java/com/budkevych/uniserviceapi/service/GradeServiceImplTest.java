package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.model.Student;
import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.repository.GradeRepository;
import com.budkevych.uniserviceapi.service.impl.GradeServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GradeServiceImplTest {
    @InjectMocks
    private GradeServiceImpl gradeService;
    @Mock
    private GradeRepository gradeRepository;

    @Test
    void addOk() {
        Grade grade = getGrade();
        Grade fromDB = getGrade();
        fromDB.setId(1L);
        Grade expected = getGrade();
        expected.setId(1L);

        Mockito.when(gradeRepository.save(grade)).thenReturn(fromDB);

        Grade actual = gradeService.add(grade);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOk() {
        Grade fromDB = getGrade();
        fromDB.setId(1L);
        Grade expected = getGrade();
        expected.setId(1L);

        Mockito.when(gradeRepository.findById(fromDB.getId()))
                .thenReturn(Optional.of(fromDB));

        Grade actual = gradeService.get(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateOk() {
        Grade grade = getGrade();
        Grade fromDB = getGrade();
        fromDB.setId(1L);
        Grade expected = getGrade();
        expected.setId(1L);

        Mockito.when(gradeRepository.save(grade)).thenReturn(fromDB);
        Mockito.when(gradeRepository.findById(1L))
                .thenReturn(Optional.of(fromDB));

        Grade actual = gradeService.update(1L, grade);
        Assertions.assertEquals(expected, actual);
    }

    private Grade getGrade() {
        return Grade.builder()
                .grade(5)
                .subject(Subject.builder()
                        .id(1L)
                        .build())
                .student(Student.builder()
                        .id(1L)
                        .build())
                .build();
    }
}