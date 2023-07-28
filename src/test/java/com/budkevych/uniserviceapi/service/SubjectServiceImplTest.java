package com.budkevych.uniserviceapi.service;

import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.repository.SubjectRepository;
import com.budkevych.uniserviceapi.service.impl.SubjectServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubjectServiceImplTest {
    @InjectMocks
    private SubjectServiceImpl subjectService;
    @Mock
    private SubjectRepository subjectRepository;

    @Test
    void addOk() {
        Subject grade = getSubject();
        Subject fromDB = getSubject();
        fromDB.setId(1L);
        Subject expected = getSubject();
        expected.setId(1L);

        Mockito.when(subjectRepository.save(grade)).thenReturn(fromDB);

        Subject actual = subjectService.add(grade);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getOk() {
        Subject fromDB = getSubject();
        fromDB.setId(1L);
        Subject expected = getSubject();
        expected.setId(1L);

        Mockito.when(subjectRepository.findById(fromDB.getId()))
                .thenReturn(Optional.of(fromDB));

        Subject actual = subjectService.get(1L);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateOk() {
        Subject grade = getSubject();
        Subject fromDB = getSubject();
        fromDB.setId(1L);
        Subject expected = getSubject();
        expected.setId(1L);

        Mockito.when(subjectRepository.save(grade)).thenReturn(fromDB);
        Mockito.when(subjectRepository.findById(1L))
                .thenReturn(Optional.of(fromDB));

        Subject actual = subjectService.update(1L, grade);
        Assertions.assertEquals(expected, actual);
    }

    private Subject getSubject() {
        return new Subject(0L, "Math");
    }
}