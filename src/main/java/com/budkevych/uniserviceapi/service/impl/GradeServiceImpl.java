package com.budkevych.uniserviceapi.service.impl;

import com.budkevych.uniserviceapi.model.Grade;
import com.budkevych.uniserviceapi.repository.GradeRepository;
import com.budkevych.uniserviceapi.service.GradeService;
import com.budkevych.uniserviceapi.service.StudentService;
import com.budkevych.uniserviceapi.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final SubjectService subjectService;
    private final StudentService studentService;

    @Override
    public Grade add(Grade grade) {
        grade.setSubject(subjectService.get(grade.getSubject().getId()));
        grade.setStudent(studentService.get(grade.getStudent().getId()));
        return gradeRepository.save(grade);
    }

    @Override
    public Grade get(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No grade found for id " + id));
    }

    @Override
    public Grade update(Long id, Grade grade) {
        get(id);
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    @Override
    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }
}
