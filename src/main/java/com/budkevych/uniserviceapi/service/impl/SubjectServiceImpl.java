package com.budkevych.uniserviceapi.service.impl;

import com.budkevych.uniserviceapi.model.Subject;
import com.budkevych.uniserviceapi.repository.SubjectRepository;
import com.budkevych.uniserviceapi.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Override
    public Subject add(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject get(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No subject found for id " + id));
    }

    @Override
    public Subject update(Long id, Subject subject) {
        get(id);
        subject.setId(id);
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
