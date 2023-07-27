package com.budkevych.uniserviceapi.repository;

import com.budkevych.uniserviceapi.model.Student;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired private StudentRepository studentRepository;

    @Test
    void injectedComponentsAreNotNull(){
        Assertions.assertNotEquals(studentRepository, null);
    }

    @Test
    public void student_whenSave_thenGetOk() {
        Student student = Student.builder()
                .id(1L)
                .name("a")
                .surname("b")
                .age(15)
                .teachers(new HashSet<>())
                .grades(new ArrayList<>())
                .build();
        studentRepository.save(student);

        Student student2 = studentRepository.findById(1L).get();
        Assertions.assertEquals("a", student2.getName());
    }
}